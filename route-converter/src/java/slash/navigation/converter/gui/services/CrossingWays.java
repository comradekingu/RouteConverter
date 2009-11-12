/*
    This file is part of RouteConverter.

    RouteConverter is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    RouteConverter is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with RouteConverter; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

    Copyright (C) 2007 Christian Pesch. All Rights Reserved.
*/

package slash.navigation.converter.gui.services;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import slash.common.hex.HexEncoder;
import slash.common.io.Files;
import slash.common.io.InputOutput;
import slash.navigation.NavigationFileParser;
import slash.navigation.gpx.Gpx10Format;
import slash.navigation.rest.Post;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Logger;

/**
 * The {@link RouteService} at http://crossingways.com
 *
 * @author Christian Pesch
 */

public class CrossingWays implements RouteService {
    private static final Logger log = Logger.getLogger(CrossingWays.class.getName());

    public String getName() {
        return "crossingways";
    }

    public boolean isOriginOf(String url) {
        return url.startsWith("http://www.crossingways.com/services/");
    }

    private String readFile(File file) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputOutput inout = new InputOutput(new FileInputStream(file), baos);
        inout.start();
        return baos.toString();
    }

    private String sha1(String text) throws IOException {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            // Java6: throw new IOException(e);
            throw createIOException(e);
        }
        messageDigest.update(text.getBytes("UTF-8"), 0, text.length());
        byte[] bytes = messageDigest.digest();
        return HexEncoder.encodeBytes(bytes);
    }

    String extractResult(String result) throws IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            // Java6: throw new IOException(e);
            throw createIOException(e);
        }

        Document document;
        try {
            document = builder.parse(new InputSource(new StringReader(result)));
        } catch (SAXException e) {
            // Java6: throw new IOException(e);
            throw createIOException(e);
        }

        NodeList nodeList = document.getChildNodes();
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            return node.getTextContent();
        }
        return null;
    }

    private static IOException createIOException(Exception e) {
        IOException exception = new IOException();
        exception.setStackTrace(e.getStackTrace());
        return exception;
    }

    public void upload(String userName, String password, String url, String name, String description) throws IOException {
        NavigationFileParser parser = new NavigationFileParser();
        List<URL> urls = Files.toUrls(url);
        if (urls.size() == 0)
            throw new IOException("Cannot read url " + url);
        if (!parser.read(urls.iterator().next()))
            throw new IOException("Cannot parse url " + url);
        File tempFile = File.createTempFile("crossingwaysclient", ".xml");
        // TODO extend write to write to OutputStream and File
        parser.write(parser.getTheRoute(), new Gpx10Format(), false, false, tempFile);
        String gpx = readFile(tempFile);
        if (!tempFile.delete())
            throw new IOException("Cannot delete temp file " + tempFile);

        Post post = new Post("http://www.crossingways.com/services/LiveTracking.asmx/UploadGPX");
        String body = "username=" + userName + "&password=" + sha1(password) + "&trackname=" + name + "&gpx=" + gpx;
        post.setBody(body);
        log.info("Body: " + body);
        String resultBody = post.execute();
        log.info("ResultBody: " + resultBody);
        String result = extractResult(resultBody);
        log.info("Result: " + result);
        if (!"Track saved! Have a nice Day!".equals(result))
            throw new IOException("Cannot upload url: " + result);
    }
}
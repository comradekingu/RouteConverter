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

package slash.navigation.url;

import org.junit.Test;
import slash.navigation.base.BaseNavigationFormat;
import slash.navigation.base.BaseNavigationPosition;
import slash.navigation.base.BaseRoute;
import slash.navigation.base.NavigationFormatParser;
import slash.navigation.base.ParserResult;
import slash.navigation.kml.Kml20Format;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static slash.navigation.base.NavigationTestCase.SAMPLE_PATH;

public class GoogleMapsUrlFormatIT {

    private void checkBookmark(String name) throws IOException {
        File source = new File(SAMPLE_PATH + name);
        NavigationFormatParser parser = new NavigationFormatParser();
        ParserResult result = parser.read(source);
        assertNotNull(result);
        assertEquals(Kml20Format.class, result.getFormat().getClass());
        assertEquals(2, result.getAllRoutes().size());
        BaseRoute<BaseNavigationPosition, BaseNavigationFormat> route = result.getTheRoute();
        assertEquals(4, route.getPositionCount());
        BaseNavigationPosition position = route.getPositions().get(route.getPositionCount() - 1);
        assertEquals("Arrive at: S Tamiami Trail", position.getComment());
        assertNotNull(position.getLongitude());
        assertNotNull(position.getLatitude());
    }

    @Test
    public void testOriginalBookmark() throws IOException {
        checkBookmark("4a-original.url");
    }

    @Test
    public void testBookmarkWrittenByFirefox() throws IOException {
        checkBookmark("4a-modified-by-firefox.url");
    }

    @Test
    public void testBookmarkWrittenByIE() throws IOException {
        checkBookmark("4a-modified-by-ie.url");
    }

    @Test
    public void testBookmarkWrittenByOpera() throws IOException {
        checkBookmark("4a-modified-by-opera.url");
    }
}
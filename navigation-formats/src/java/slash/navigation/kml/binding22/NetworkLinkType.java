//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-646 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.02.10 at 11:29:17 AM CET 
//


package slash.navigation.kml.binding22;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NetworkLinkType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NetworkLinkType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/kml/2.2}AbstractFeatureType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/kml/2.2}refreshVisibility" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/kml/2.2}flyToView" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/kml/2.2}Url" minOccurs="0"/>
 *           &lt;element ref="{http://www.opengis.net/kml/2.2}Link" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.opengis.net/kml/2.2}NetworkLinkSimpleExtensionGroup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/kml/2.2}NetworkLinkObjectExtensionGroup" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NetworkLinkType", propOrder = {
    "rest"
})
public class NetworkLinkType
    extends AbstractFeatureType
{

    @XmlElementRefs({
        @XmlElementRef(name = "refreshVisibility", namespace = "http://www.opengis.net/kml/2.2", type = JAXBElement.class),
        @XmlElementRef(name = "NetworkLinkObjectExtensionGroup", namespace = "http://www.opengis.net/kml/2.2", type = JAXBElement.class),
        @XmlElementRef(name = "Url", namespace = "http://www.opengis.net/kml/2.2", type = JAXBElement.class),
        @XmlElementRef(name = "Link", namespace = "http://www.opengis.net/kml/2.2", type = JAXBElement.class),
        @XmlElementRef(name = "flyToView", namespace = "http://www.opengis.net/kml/2.2", type = JAXBElement.class),
        @XmlElementRef(name = "NetworkLinkSimpleExtensionGroup", namespace = "http://www.opengis.net/kml/2.2", type = JAXBElement.class)
    })
    protected List<JAXBElement<?>> rest;

    /**
     * Gets the rest of the content model. 
     * 
     * <p>
     * You are getting this "catch-all" property because of the following reason: 
     * The field name "Link" is used by two different parts of a schema. See: 
     * line 744 of file:/C:/p4/RouteConverter/trunk/navigation-formats/src/doc/kml/ogckml22.xsd
     * line 312 of file:/C:/p4/RouteConverter/trunk/navigation-formats/src/doc/kml/ogckml22.xsd
     * <p>
     * To get rid of this property, apply a property customization to one 
     * of both of the following declarations to change their names: 
     * Gets the value of the rest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link LinkType }{@code >}
     * {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}
     * {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * {@link JAXBElement }{@code <}{@link LinkType }{@code >}
     * {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getRest() {
        if (rest == null) {
            rest = new ArrayList<JAXBElement<?>>();
        }
        return this.rest;
    }

}

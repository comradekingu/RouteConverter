//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-646 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.02.10 at 11:29:17 AM CET 
//


package slash.navigation.kml.binding22;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AliasType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AliasType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/kml/2.2}AbstractObjectType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/kml/2.2}targetHref" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/kml/2.2}sourceHref" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/kml/2.2}AliasSimpleExtensionGroup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/kml/2.2}AliasObjectExtensionGroup" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AliasType", propOrder = {
    "targetHref",
    "sourceHref",
    "aliasSimpleExtensionGroup",
    "aliasObjectExtensionGroup"
})
public class AliasType
    extends AbstractObjectType
{

    @XmlSchemaType(name = "anyURI")
    protected String targetHref;
    @XmlSchemaType(name = "anyURI")
    protected String sourceHref;
    @XmlElement(name = "AliasSimpleExtensionGroup")
    @XmlSchemaType(name = "anySimpleType")
    protected List<Object> aliasSimpleExtensionGroup;
    @XmlElement(name = "AliasObjectExtensionGroup")
    protected List<AbstractObjectType> aliasObjectExtensionGroup;

    /**
     * Gets the value of the targetHref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetHref() {
        return targetHref;
    }

    /**
     * Sets the value of the targetHref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetHref(String value) {
        this.targetHref = value;
    }

    /**
     * Gets the value of the sourceHref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceHref() {
        return sourceHref;
    }

    /**
     * Sets the value of the sourceHref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceHref(String value) {
        this.sourceHref = value;
    }

    /**
     * Gets the value of the aliasSimpleExtensionGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aliasSimpleExtensionGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAliasSimpleExtensionGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getAliasSimpleExtensionGroup() {
        if (aliasSimpleExtensionGroup == null) {
            aliasSimpleExtensionGroup = new ArrayList<Object>();
        }
        return this.aliasSimpleExtensionGroup;
    }

    /**
     * Gets the value of the aliasObjectExtensionGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aliasObjectExtensionGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAliasObjectExtensionGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AbstractObjectType }
     * 
     * 
     */
    public List<AbstractObjectType> getAliasObjectExtensionGroup() {
        if (aliasObjectExtensionGroup == null) {
            aliasObjectExtensionGroup = new ArrayList<AbstractObjectType>();
        }
        return this.aliasObjectExtensionGroup;
    }

}

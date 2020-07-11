package xmlteam4.userservice.soap.classes;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Message complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Message"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="sender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="reciever" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dateSent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Message", propOrder = {
        "id",
        "sender",
        "reciever",
        "content",
        "dateSent"
})
public class Message {

    protected Long id;
    protected String sender;
    protected String reciever;
    protected String content;
    protected String dateSent;

    /**
     * Gets the value of the id property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the sender property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSender() {
        return sender;
    }

    /**
     * Sets the value of the sender property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSender(String value) {
        this.sender = value;
    }

    /**
     * Gets the value of the reciever property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getReciever() {
        return reciever;
    }

    /**
     * Sets the value of the reciever property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setReciever(String value) {
        this.reciever = value;
    }

    /**
     * Gets the value of the content property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the dateSent property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDateSent() {
        return dateSent;
    }

    /**
     * Sets the value of the dateSent property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDateSent(String value) {
        this.dateSent = value;
    }

}

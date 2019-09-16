
package com.example.springbootwebservice.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>country complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="country">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="population" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="capital" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="currency" type="{http://127.0.0.1:8080/ws}currency"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "country", namespace = "http://127.0.0.1:8080/ws", propOrder = {
        "name",
        "population",
        "capital",
        "currency"
})
public class Country {

    @XmlElement(namespace = "http://127.0.0.1:8080/ws", required = true)
    protected String name;
    @XmlElement(namespace = "http://127.0.0.1:8080/ws")
    protected int population;
    @XmlElement(namespace = "http://127.0.0.1:8080/ws", required = true)
    protected String capital;
    @XmlElement(namespace = "http://127.0.0.1:8080/ws", required = true)
    @XmlSchemaType(name = "string")
    protected Currency currency;

    /**
     * ��ȡname���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * ����name���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * ��ȡpopulation���Ե�ֵ��
     */
    public int getPopulation() {
        return population;
    }

    /**
     * ����population���Ե�ֵ��
     */
    public void setPopulation(int value) {
        this.population = value;
    }

    /**
     * ��ȡcapital���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getCapital() {
        return capital;
    }

    /**
     * ����capital���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCapital(String value) {
        this.capital = value;
    }

    /**
     * ��ȡcurrency���Ե�ֵ��
     *
     * @return possible object is
     * {@link Currency }
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * ����currency���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link Currency }
     */
    public void setCurrency(Currency value) {
        this.currency = value;
    }

}

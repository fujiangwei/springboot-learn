
package com.example.springbootwebservice.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="country" type="{http://127.0.0.1:8080/ws}country"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "country"
})
@XmlRootElement(name = "getCountryResponse", namespace = "http://127.0.0.1:8080/ws")
public class GetCountryResponse {

    @XmlElement(namespace = "http://127.0.0.1:8080/ws", required = true)
    protected Country country;

    /**
     * ��ȡcountry���Ե�ֵ��
     *
     * @return possible object is
     * {@link Country }
     */
    public Country getCountry() {
        return country;
    }

    /**
     * ����country���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link Country }
     */
    public void setCountry(Country value) {
        this.country = value;
    }

}

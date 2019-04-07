# XSD(Xml Schema Definition)详解
## Xml Schema的用途
  1．  定义一个Xml文档中都有什么元素
  
  2．  定义一个Xml文档中都会有什么属性
  
  3．  定义某个节点的都有什么样的子节点，可以有多少个子节点，子节点出现的顺序
  
  4．  定义元素或者属性的数据类型
  
  5．  定义元素或者属性的默认值或者固定值
  
## Xml Schema的根元素
  <?xml version="1.0"?>
  
  <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema" 表示数据类型等定义来自w3
  
  targetNamespace="http://www.w3schools.com" 表示文档中要定义的元素来自什么命名空间
  
  xmlns="http://www.w3schools.com"表示此文档的默认命名空间是什么
  
  elementFormDefault="qualified"> 表示要求xml文档的每一个元素都要有命名空间指定
  
  ……定义主体部分……
  
  </xs:schema>
  
>  如何定义一个简单元素
  
  <xs:element  此处表示要定义一个元素
  
  name=”color” 表示要定义元素的名称
  
  type=”xs:string”  表示要定义元素的数据类型
  
  default=”red” 表示定义元素的默认值
  
  fixed=”red”/> 表示要定义元素的固定值，此元素只可以取“red”值
  
  以上定义了一个简单元素，元素实例：<color>red</color>
  
>  如何定义一个属性
  <xs:attribute
  
           name=”birthday” 表示要定义属性的名字
  
           type=”xs:date” 表示要定义属性的数据类型
  
           default=”2001-01-11” 表示要定义属性的默认值
  
           fixed=”2001-01-11” 表示要定义属性的固定值
  
           use=”required”/> 表示此属性是否是必须指定的，即如果不指定就不符合Schema，默认没有use=”required”属性表示属性可有可无
  
  
  
>  如何定义元素或者属性值的限制

  1．最大值最小值限制
  
      <xs:element name="age">
      
      <xs:simpleType>
      
          <xs:restriction base="xs:integer">
          
          <xs:minInclusive value="0"/> 大于等于0，<xs: minExclusive>表示最小值但是不包括指定值
          
           <xs:maxInclusive value="120"/> 小于等于120，<xs: maxExclusive>表示最大值但是不包括指定值
          
          </xs:restriction>
      
      </xs:simpleType>
      
      </xs:element>
  
  2．枚举限制，指只能在指定的几个值中取值
  
    <xs:element name="car" type="carType"/>
  
    <xs:simpleType name="carType">
  
        <xs:restriction base="xs:string">
      
          <xs:enumeration value="Audi"/>
      
          <xs:enumeration value="Golf"/>
      
          <xs:enumeration value="BMW"/>
      
        </xs:restriction>
  
    </xs:simpleType>
  
  3．模式（pattern）限制 ，指字符串的格式必须满足制定的匹配模式
  例子
  
      <xs:element name="letter">
      <xs:simpleType>
        <xs:restriction base="xs:string">
          <xs:pattern value="[a-z]"/>
        </xs:restriction>
      </xs:simpleType>
      </xs:element>
  说明:表示只能在小写字母中取一个值
  
      <xs:element name="initials">
      <xs:simpleType>
        <xs:restriction base="xs:string">
          <xs:pattern value="[A-Z][A-Z][A-Z]"/>
        </xs:restriction>
      </xs:simpleType>
      </xs:element> 
  表示必须是三个大写字母
  
      <xs:element name="initials">
      <xs:simpleType>
        <xs:restriction base="xs:string">
          <xs:pattern value="[a-zA-Z][a-zA-Z][a-zA-Z]"/>
        </xs:restriction>
      </xs:simpleType>
      </xs:element> 
  表示必须是三个字母，可以是大写或小写的
  
      <xs:element name="choice">
      <xs:simpleType>
        <xs:restriction base="xs:string">
          <xs:pattern value="[xyz]"/>
        </xs:restriction>
      </xs:simpleType>
      </xs:element>
  
  表示必须是xyz中的一个
  
      <xs:element name="prodid">
      <xs:simpleType>
        <xs:restriction base="xs:integer">
          <xs:pattern value="[0-9][0-9][0-9][0-9][0-9]"/>
        </xs:restriction>
      </xs:simpleType>
      </xs:element> 
  表示数字的范围是0-99999
  
      <xs:element name="letter">
      <xs:simpleType>
        <xs:restriction base="xs:string">
          <xs:pattern value="([a-z])*"/>
        </xs:restriction>
      </xs:simpleType>
      </xs:element> 
  表示必须是0或者多个小写字符组成的序列
  
      <xs:element name="letter">
      <xs:simpleType>
        <xs:restriction base="xs:string">
          <xs:pattern value="([a-z][A-Z])+"/>
        </xs:restriction>
      </xs:simpleType>
      </xs:element> 
  表示必须是多个字母。
  
      <xs:element name="gender">
      <xs:simpleType>
        <xs:restriction base="xs:string">
          <xs:pattern value="male|female"/>
        </xs:restriction>
      </xs:simpleType>
      </xs:element> 
  表示是male或者female中的一个
  
      <xs:element name="password">
      <xs:simpleType>
        <xs:restriction base="xs:string">
          <xs:pattern value="[a-zA-Z0-9]{8}"/>
        </xs:restriction>
      </xs:simpleType>
      </xs:element> 
  表示必须是8个字母数字字符
  
  4．字符串长度的限制
    
    <xs:element name="password">
  
    <xs:simpleType>
  
        <xs:restriction base="xs:string">
      
          <xs:length value="8"/>
      
        </xs:restriction>
  
    </xs:simpleType>
  
    </xs:element>
  
  长度必须是8。
  
    <xs:element name="password">
  
    <xs:simpleType>
  
        <xs:restriction base="xs:string">
      
          <xs:minLength value="5"/>
      
          <xs:maxLength value="8"/>
      
        </xs:restriction>
  
    </xs:simpleType>
  
    </xs:element>
  
  表示长度在5-8之间
  
  5． 对于空白字符的限制
  示例:
  
      <xs:element name="address">
      <xs:simpleType>
        <xs:restriction base="xs:string">
          <xs:whiteSpace value="preserve"/>
        </xs:restriction>
      </xs:simpleType>
      </xs:element>
  说明:保留原样，表示xml处理器不会移除或者替换任何空白字符
  
        <xs:element name="address">
        <xs:simpleType>
        <xs:restriction base="xs:string">
          <xs:whiteSpace value="replace"/>
        </xs:restriction>
        </xs:simpleType>
        </xs:element> 
  指回车，换行，Tab都会被替换成空格处理
  
      <xs:element name="address">
      <xs:simpleType>
        <xs:restriction base="xs:string">
          <xs:whiteSpace value="collapse"/>
        </xs:restriction>
      </xs:simpleType>
      </xs:element> 
  去掉多于一个空格，和html中处理方式相同
  
  如何定义复杂类型
  复杂类型是指定义元素中包含属性或者子元素的类型
  
  1． 定义只包含子元素的复杂类型
  
      <xs:element name="person">
      
        <xs:complexType>
      
          <xs:sequence>
      
            <xs:element name="firstname" type="xs:string"/>
      
            <xs:element name="lastname" type="xs:string"/>
      
          </xs:sequence>
      
        </xs:complexType>
      
      </xs:element>
  
  2． 定义只包含属性的复杂类型
  
      <xs:element name="product" type="prodtype"/>
      
      <xs:complexType name="prodtype">
      
        <xs:attribute name="prodid" type="xs:positiveInteger"/>
      
      </xs:complexType>
  
  3． 定义只包含内容的复杂类型
  
      <xs:element name="shoesize" type="shoetype"/>
      
      <xs:complexType name="shoetype">
      
        <xs:simpleContent>
      
          <xs:extension base="xs:integer">
      
            <xs:attribute name="country" type="xs:string" />
      
          </xs:extension>
      
        </xs:simpleContent>
      
      </xs:complexType>
  
  4． 定义包含内容和子元素混合的复杂类型
  
      <xs:element name="letter">
      
        <xs:complexType mixed="true">
      
          <xs:sequence>
      
            <xs:element name="name" type="xs:string"/>
      
            <xs:element name="orderid" type="xs:positiveInteger"/>
      
            <xs:element name="shipdate" type="xs:date"/>
      
          </xs:sequence>
      
        </xs:complexType>
      
      </xs:element>
  
  以上定义对应的Xml
  
      <letter>
      
      Dear Mr.<name>John Smith</name>.
      
      Your order <orderid>1032</orderid>
      
      will be shipped on <shipdate>2001-07-13</shipdate>.
      
      </letter>
  
  
  5． 定义包含属性和子元素的复杂类型
  使用指示器
  在Xsd中的指示器包括
  
  1． 顺序指示器
  1） All
  
  指示子元素可以以任何顺序出现，并且每一个元素都必须出现一次
  
      <xs:element name="person">
      
        <xs:complexType>
      
          <xs:all>
      
            <xs:element name="firstname" type="xs:string"/>
      
            <xs:element name="lastname" type="xs:string"/>
      
          </xs:all>
      
        </xs:complexType>
      
      </xs:element>
  
  2） Choice
  
  指示子元素中可以出现一个或者另一个
  
      <xs:element name="person">
      
        <xs:complexType>
      
          <xs:choice>
      
            <xs:element name="employee" type="employee"/>
      
            <xs:element name="member" type="member"/>
      
          </xs:choice>
      
        </xs:complexType>
      
      </xs:element>
  
  3） Sequence
  
  指示子元素必须按照顺序出现
  
      <xs:element name="person">
      
        <xs:complexType>
      
          <xs:sequence>
      
            <xs:element name="firstname" type="xs:string"/>
      
            <xs:element name="lastname" type="xs:string"/>
      
          </xs:sequence>
      
        </xs:complexType>
      
      </xs:element>
  
  2． 出现次数指示器minOccurs，maxOccurs
  <xs:element name="person">
  
    <xs:complexType>
  
      <xs:sequence>
  
        <xs:element name="full_name" type="xs:string"/>
  
        <xs:element name="child_name" type="xs:string"
  
        maxOccurs="10" minOccurs="0"/>
  
      </xs:sequence>
  
    </xs:complexType>
  
  </xs:element>
  
  3． 组指示器（group Indicators）
  用来定义相关的一组元素
  
      <xs:group name="persongroup">
      
        <xs:sequence>
      
          <xs:element name="firstname" type="xs:string"/>
      
          <xs:element name="lastname" type="xs:string"/>
      
          <xs:element name="birthday" type="xs:date"/>
      
        </xs:sequence>
      
      </xs:group>
      
      <xs:element name="person" type="personinfo"/>
      
      <xs:complexType name="personinfo">
      
        <xs:sequence>
      
          <xs:group ref="persongroup"/>
      
          <xs:element name="country" type="xs:string"/>
      
        </xs:sequence>
      
      </xs:complexType>
  
  用来定义一组相关的属性
  
      <xs:attributeGroup name="personattrgroup">
      
        <xs:attribute name="firstname" type="xs:string"/>
      
        <xs:attribute name="lastname" type="xs:string"/>
      
        <xs:attribute name="birthday" type="xs:date"/>
      
      </xs:attributeGroup>
      
      <xs:element name="person">
      
        <xs:complexType>
      
          <xs:attributeGroup ref="personattrgroup"/>
      
        </xs:complexType>
      
      </xs:element>
  
  
>  Any关键字

  表示可以有任意元素
  
      <xs:element name="person">
      
        <xs:complexType>
      
          <xs:sequence>
      
            <xs:element name="firstname" type="xs:string"/>
      
            <xs:element name="lastname" type="xs:string"/>
      
            <xs:any minOccurs="0"/>
      
          </xs:sequence>
      
        </xs:complexType>
      
      </xs:element>
  
>  anyAttribute关键字

  表示可以有任意属性
  
      <xs:element name="person">
      
        <xs:complexType>
      
          <xs:sequence>
      
            <xs:element name="firstname" type="xs:string"/>
      
            <xs:element name="lastname" type="xs:string"/>
      
          </xs:sequence>
      
          <xs:anyAttribute/>
      
        </xs:complexType>
      
      </xs:element>
  
>  substitutionGroup关键字

  表示某一个元素和另一个替代元素定义相同
  
      <xs:element name="name" type="xs:string"/>
      
      <xs:element name="navn" substitutionGroup="name"/>
      
      <xs:complexType name="custinfo">
      
        <xs:sequence>
      
          <xs:element ref="name"/>
      
        </xs:sequence>
      
      </xs:complexType>
      
      <xs:element name="customer" type="custinfo"/>
      
      <xs:element name="kunde" substitutionGroup="customer"/>
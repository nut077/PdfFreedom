### Tutorial PdfFreedom
* **maven**<br>
```
<!-- https://mvnrepository.com/artifact/com.github.nut077/pdf-freedom -->
<dependency>
    <groupId>com.github.nut077</groupId>
    <artifactId>pdf-freedom</artifactId>
    <version>1.0.2</version>
</dependency>
```
* **รูปแบบการใช้งาน**<br>
```<table></table>``` คือ 1 ตาราง ```<tr></tr>``` คือ 1 แถว ```<td></td>``` คือ 1 column
    ```java
    StringBuilder table = new StringBuilder();
    table.append("<table>");
      table.append("<tr>");
        table.append("<td>A</td>");
        table.append("<td>B</td>");
        table.append("<td>C</td>");
      table.append("</tr>");
    table.append("</table>");
    
    StringBuilder header = new StringBuilder();
    header.append("<table>");
      header.append("<tr>");
        header.append("<td>Header A</td>");
        header.append("<td>Header B</td>");
        header.append("<td>Header CC</td>");
      header.append("</tr>");
    header.append("</table>");
    
    /* ใช้งานแบบ offline
    parameter ตัวแรกคือที่อยู่ของไฟล์ที่จะสร้าง 
    ตัวที่สองคือชื่อของไฟล์ pdf 
    ตัวที่สามคือที่อยู่ของฟ้อนต์ ตัวที่สี่คือชื่อฟ้อนต์ 
    ตัวที่ห้าคือชื่อของไฟล์ pdf
    ตัวที่หกคือข้อมูลที่จะเขียนลงในไฟล์ pdf 
    และตัวสุดท้ายคือ header ที่จะออกมาทุกแผ่นของไฟล์ จะมีหรือไม่มีก็ได้ */
    
    PdfFreedom pdfFreedom = new PdfFreedom("D://", "pdf", "D://", "THSarabun", "example pdf", table.toString(), header.toString());

    /* ใช้งานแบบ servlets ไฟล์ jsp
    parameter ตัวแรกคือ application 
    ตัวที่สองคือ response 
    ตัวที่สามคือ out
    ตัวที่สี่คือชื่อของฟอนต์ ฟ้อนต์ต้องอยู่ใน path -> FONTS/
    ตัวที่ห้าคือชื่อของไฟล์ pdf 
    ตัวที่หกคือข้อมูลที่จะเขียนลงในไฟล์ pdf 
    และตัวสุดท้ายคือ header ที่จะออกมาทุกแผ่นของไฟล์ จะมีหรือไม่มีก็ได้ */
    
    PdfFreedom pdfFreedom = new PdfFreedom(application, response, out, "tahoma", "example pdf", table.toString(), header.toString());

    pdfFreedom.write(); // สั่งให้เขียนไฟล์ 
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s22.postimg.cc/y19osylgx/Capture.png)](https://postimg.cc/image/qlaf75xrh/)
    <br><br>
    
* **ผสานเซลล์ แนวนอน**<br>
ใช้ tag ```<colspan>ตัวเลขที่ต้องการ</colspan>```
    ```java
    table.append("<tr>");
        table.append("<td><colspan>2</colspan>colspan 2</td>");
        table.append("<td><colspan>3</colspan>colspan 3</td>");
    table.append("</tr>");
    table.append("<tr>");
        table.append("<td>1</td>");
        table.append("<td>2</td>");
        table.append("<td>3</td>");
        table.append("<td>4</td>");
        table.append("<td>5</td>");
    table.append("</tr>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s22.postimg.cc/4pxtzq1ox/Capture.png)](https://postimg.cc/image/lqgq8eeq5/)
    <br><br>
    
* **ผสานเซลล์ แนวตั้ง**<br>
ใช้ tag ```<rowspan>ตัวเลขที่ต้องการ</rowspan>```
    ```java
    table.append("<tr>");
        table.append("<td><rowspan>2</rowspan>rowspan 2</td>");
        table.append("<td>A</td>");
    table.append("</tr>");
    table.append("<tr>");
        table.append("<td>B</td>");
    table.append("</tr>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s22.postimg.cc/olttfn8k1/Capture.png)](https://postimg.cc/image/gt35no2kt/)
    <br><br>
    
* **ขยายขนาดความกว้างของคอลัมน์**<br>
ใช้ tag ```<width>ตัวเลขที่ต้องการ</width>```
    ```java
    table.append("<td><width>10</width>width 10</td>");
    table.append("<td><width>20</width>width 20</td>");
    table.append("<td><width>30</width>width 30</td>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s22.postimg.cc/sjh34yvch/Capture.png)](https://postimg.cc/image/dnijxdjxp/)
    <br><br>
    
* **ขยายขนาดความสูงของคอลัมน์**<br>
ใช้ tag ```<height>ตัวเลขที่ต้องการ</height>```
    ```java
    table.append("<tr>");
        table.append("<td><height>500</height>height 500</td>");
    table.append("</tr>");
    table.append("<tr>");
        table.append("<td><height>600</height>height 600</td>");
    table.append("</tr>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s22.postimg.cc/6yc0hge29/Capture.png)](https://postimg.cc/image/dc13kpiy5/)
    <br><br>
    
* **การจัดรูปแบบ**<br>
ใช้ tag ```<format>รูปแบบตาม list ข้างล่าง</format>``` ค่าเริ่มต้นคือ border-center
    - left
    - left-middle
    - left-top
    - center
    - center-middle
    - center-top
    - right
    - right -middle
    - right -top
    - border-left
    - border-left-middle
    - border-left-top
    - border-center
    - border-center-middle
    - border-center-top
    - border-right
    - border-right-middle
    - border-right-top
    ```java
    table.append("<tr>");
        table.append("<td><format>left</format>left</td>");
        table.append("<td><format>left-middle</format>left-middle</td>");
        table.append("<td><format>left-top</format>left-top</td>");
    table.append("</tr>");
    table.append("<tr>");
        table.append("<td><format>center</format>center</td>");
        table.append("<td><format>center-middle</format>center-middle</td>");
        table.append("<td><format>center-top</format>center-top</td>");
    table.append("</tr>");
    table.append("<tr>");
        table.append("<td><format>right</format>right</td>");
        table.append("<td><format>right-middle</format>right-middle</td>");
        table.append("<td><format>right-top</format>right-top</td>");
    table.append("</tr>");
    table.append("<tr>");
        table.append("<td><format>border-left</format>border-left</td>");
        table.append("<td><format>border-left-middle</format>border-left-middle</td>");
        table.append("<td><format>border-left-top</format>border-left-top</td>");
    table.append("</tr>");
    table.append("<tr>");
        table.append("<td><format>border-center</format>border-center</td>");
        table.append("<td><format>border-center-middle</format>border-center-middle</td>");
        table.append("<td><format>border-center-top</format>border-center-top</td>");
    table.append("</tr>");
    table.append("<tr>");
        table.append("<td><format>border-right</format>border-right</td>");
        table.append("<td><format>border-right-middle</format>border-right-middle</td>");
        table.append("<td><format>border-right-top</format>border-right-top</td>");
    table.append("</tr>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s22.postimg.cc/543rdhyg1/Capture.png)](https://postimg.cc/image/ixs42jr19/)
    <br><br>
    กำหนดเส้นขอบให้แต่ละมุม<br>
    ใช้ tag ``` <border-left>true or false</border-left> ``` สำหรับเส้นขอบซ้าย ค่าเริ่มต้นคือ false<br>
    ใช้ tag ``` <border-right>true or false</border-right> ``` สำหรับเส้นขอบขวา ค่าเริ่มต้นคือ false<br>
    ใช้ tag ``` <border-top>true or false</border-top> ``` สำหรับเส้นขอบบน ค่าเริ่มต้นคือ false<br>
    ใช้ tag ``` <border-bottom>true or false</border-bottom> ``` สำหรับเส้นขอบล่าง ค่าเริ่มต้นคือ false<br>
    ```java
    table.append("<tr><td><border-left>true</border-left>border-left</td></tr>");
    table.append("<tr><td><border-right>true</border-right>border-right</td></tr>");
    table.append("<tr><td><border-top>true</border-top>border-top</td></tr>");
    table.append("<tr><td><border-bottom>true</border-bottom>border-bottom</td></tr>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s33.postimg.cc/kb05rcjvz/Capture.png)](https://postimg.cc/image/w045fbauj/)
    <br><br>
    ถ้าต้องการให้ขอบเป็นเส้นจุดให้เพิ่ม -dotted ต่อท้าย
    ```java
    table.append("<tr><td><border-left-dotted>true</border-left-dotted>border-left-dotted</td></tr>");
    table.append("<tr><td><border-right-dotted>true</border-right-dotted>border-right-dotted</td></tr>");
    table.append("<tr><td><border-top-dotted>true</border-top-dotted>border-top-dotted</td></tr>");
    table.append("<tr><td><border-bottom-dotted>true</border-bottom-dotted>border-bottom-dotted</td></tr>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s33.postimg.cc/42jxhxnxb/Capture.png)](https://postimg.cc/image/yjzs9hba3/)
    <br><br>
    ถ้าต้องการให้ขอบเป็นเส้นปะให้เพิ่ม -dashed ต่อท้าย
    ```java
    table.append("<tr><td><border-left-dashed>true</border-left-dashed>border-left-dashed</td></tr>");
    table.append("<tr><td><border-right-dashed>true</border-right-dashed>border-right-dashed</td></tr>");
    table.append("<tr><td><border-top-dashed>true</border-top-dashed>border-top-dashed</td></tr>");
    table.append("<tr><td><border-bottom-dashed>true</border-bottom-dashed>border-bottom-dashed</td></tr>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s22.postimg.cc/n37m24e69/Capture.png)](https://postimg.cc/image/nsqeehepp/)
    <br><br>
    
* **เปลี่ยนขนาด font**<br>
ใช้ tag ```<font-size>ขนาด</font-size>``` ค่าเริ่มต้นคือ 14
    ```java
    table.append("<td><font-size>14</font-size>font default</td>");
    table.append("<td><font-size>20</font-size>font size 20</td>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s33.postimg.cc/45dgfkpu7/Capture.png)](https://postimg.cc/image/ocqw7vnbf/)
    <br><br>
   

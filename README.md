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
    ตัวที่ห้าคือชื่อของ title
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
    
* **กำหนดตำแหน่งของ header**<br>
ใช้ tag ```<table-margin-top>ความสูงที่ห่างจากระยะขอบกระดาษ</table-margintop> โดยวางไว้ต่อจาก tag <table>``` ค่าเริ่มต้นคือ 40
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
      header.append("<table-margin-top>10</table-margintop>");
      header.append("<tr>");
        header.append("<td>Header A</td>");
        header.append("<td>Header B</td>");
        header.append("<td>Header CC</td>");
      header.append("</tr>");
    header.append("</table>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s22.postimg.cc/6bki1hmz5/Capture.png)](https://postimg.cc/image/nc3ea600d/)
    <br><br>
    
* **กำหนดความกว้างของตาราง**<br>
ใช้ tag ```<table-width>ขนาด 1-100</table-width> โดยวางไว้ต่อจาก tag <table>``` ค่าเริ่มต้นคือ 100
    ```java
    table.append("<table>");
        table.append("<table-width>50</table-width>");
        table.append("<tr>");
            table.append("<td>width 50</td>");
        table.append("</tr>");
    table.append("</table>");
    table.append("<table>");
        table.append("<table-width>100</table-width>");
        table.append("<tr>");
            table.append("<td>width 100</td>");
        table.append("</tr>");
    table.append("</table>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s33.postimg.cc/58cy7hw9b/Capture.png)](https://postimg.cc/image/82g3kxyff/)
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
    
* **กำหนดระยะห่างระหว่างข้อความกับขอบของคอลัมน์**<br>
ใช้ tag ตามรูปแบบด้านล่าง โดยระยะห่างอิงกับการจัดรูปแบบด้วย ตัวอย่างใช้รูปแบบ border-center ขนาดเริ่มต้นคือ 5<br>
    - ```<padding>ขนาด</padding>``` // ระยะห่างทั้งหมด บน ขวา ล่าง ซ้าย
    - ```<padding-left>ขนาด</padding-left>``` // ระยะห่างระหว่างข้อความกับด้านซ้ายของคอลัมน์
    - ```<padding-right>ขนาด</padding-right>``` // ระยะห่างระหว่างข้อความกับด้านขวาของคอลัมน์
    - ```<padding-top>ขนาด</padding-top>``` // ระยะห่างระหว่างข้อความกับด้านบนของคอลัมน์
    - ```<padding-bottom>ขนาด</padding-bottom>``` // ระยะห่างระหว่างข้อความกับด้านล่างของคอลัมน์
    ```java
    table.append("<tr><td><padding>50</padding>padding 50</td></tr>");
    table.append("<tr><td><padding-left>100</padding-left>padding left 100</td></tr>");
    table.append("<tr><td><padding-right>100</padding-right>padding right 100</td></tr>");
    table.append("<tr><td><padding-top>10</padding-top>padding top 10</td></tr>");
    table.append("<tr><td><padding-bottom>2</padding-bottom>padding bottom 2</td></tr>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s22.postimg.cc/nf1221cwh/Capture.png)](https://postimg.cc/image/5c7zath1p/)
    <br><br>
    
* **เปลี่ยนขนาด font**<br>
ใช้ tag ```<font-size>ขนาด</font-size>``` ค่าเริ่มต้นคือ 14
    ```java
    table.append("<td><font-size>14</font-size>font default</td>");
    table.append("<td><font-size>20</font-size>font size 20</td>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s33.postimg.cc/4i4ult5jz/Capture.png)](https://postimg.cc/image/cnmwjytsr/)
    <br><br>
    
* **ตัวอักษรตัวหนา**<br>
ใช้ tag ```<b>true or false</b>``` ค่าเริ่มต้นคือ false
    ```java
    table.append("<td>font normal</td>");
    table.append("<td><b>true</b>font bold</td>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s33.postimg.cc/3sm29ra8v/Capture.png)](https://postimg.cc/image/je3dtpm6z/)
    <br><br>
    
* **ตัวอักษรตัวเอียง**<br>
ใช้ tag ```<i>true or false</i>``` ค่าเริ่มต้นคือ false
    ```java
    table.append("<td>font normal</td>");
    table.append("<td><i>true</i>font italic</td>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s33.postimg.cc/w5hk09tf3/Capture.png)](https://postimg.cc/image/k3m664k6j/)
    <br><br>
    
* **ขีดเส้นใต้**<br>
ใช้ tag ```<u>true or false</u>``` ค่าเริ่มต้นคือ false
    ```java
    table.append("<td>font normal</td>");
    table.append("<td><u>true</u>font underline</td>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s33.postimg.cc/p1p20bze7/Capture.png)](https://postimg.cc/image/kskby5w4r/)
    <br><br>
    
* **ขีดเส้นใต้สองเส้น**<br>
ใช้ tag ```<double-line>true or false</double-line>``` ค่าเริ่มต้นคือ false
    ```java
    table.append("<td>font normal</td>");
    table.append("<td><double-line>true</double-line>font 2 underline</td>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s33.postimg.cc/9w1ubfelr/Capture.png)](https://postimg.cc/image/t153l6t9n/)
    <br><br>
    
* **สี**<br>
ใช้ tag ```<background-color>รูปแบบตาม list ข้างล่าง</background-color> เมื่อต้องการเปลี่ยนสีพื้นหลัง``` ค่าเริ่มต้นคือ ไม่มีสี<br>
ใช้ tag ```<font-color>รูปแบบตาม list ข้างล่าง</font-color> เมื่อต้องการเปลี่ยนสีตัวอักษร``` ค่าเริ่มต้นคือ black
    - white
    - light_gray
    - gray
    - dark_gray
    - black
    - red
    - pink
    - orange
    - yellow
    - green
    - magenta
    - cyan
    - blue
    
    [![Capture.png](https://s33.postimg.cc/z2tw9fuf3/Capture.png)](https://postimg.cc/image/4y5fo2pbv/)
    <br><br>
    
    เปลี่ยนสีพื้นหลัง
    ```java
    table.append("<td><background-color>orange</background-color>orange</td>");
    table.append("<td><background-color>green</background-color>green</td>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s33.postimg.cc/i3pj7xgb3/Capture.png)](https://postimg.cc/image/iggxe3ykr/)
    <br><br>
    เปลี่ยนสีพื้นหลังแบบกำหนดสีเอง<br>
    ใช้ tag ``` <background-color-new>กำหนดเลขสี red,green,blue</background-color-new> ```
    ```java
    table.append("<td><background-color-new>0,204,255</background-color-new>new color 0,204,255</td>");
    table.append("<td><background-color-new>204,255,51</background-color-new>new color 204,255,51</td>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s33.postimg.cc/yh9iqrr7z/Capture.png)](https://postimg.cc/image/88ye1e74b/)
    <br><br>
    เปลี่ยนสีตัวอักษร
    ```java
    table.append("<td><font-color>red</font-color>red</td>");
    table.append("<td><font-color>blue</font-color>blue</td>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s33.postimg.cc/6v6r63fkv/Capture.png)](https://postimg.cc/image/qpsss7usb/)
    <br><br>
    เปลี่ยนสีตัวอักษรแบบกำหนดสีเอง<br>
    ใช้ tag ``` <font-color-new>กำหนดเลขสี red,green,blue</font-color-new> ```
    ```java
    table.append("<td><font-color-new>0,204,255</font-color-new>new color 0,204,255</td>");
    table.append("<td><font-color-new>102,0,204</font-color-new>new color 102,0,204</td>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s33.postimg.cc/l0ck8644f/Capture.png)](https://postimg.cc/image/yu0wx7wpn/)
    <br><br>
    
* **เพิ่มรูปภาพ**<br>
ใช้ tag ```<img>ที่อยู่ของรูปภาพ</img>```
    ```java
    table.append("<td><img>D://image.jpg</img></td>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s22.postimg.cc/rsvr8vw8x/Capture.png)](https://postimg.cc/image/l2f9zg931/)
    <br><br>
    
    ปรับขนาดรูปภาพแบบเปอร์เซ็นต์<br>
    ใช้ tag ```<img-width-percent>1-100</img-width-percent>``` ค่าเริ่มต้นคือ 100<br>
    ตัวอย่างปรับเป็น 50 เปอร์เซ็นต์
    ```java
    table.append("<td><img>D://image.jpg</img><img-width-percent>50</img-width-percent></td>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s22.postimg.cc/k1f1a6f75/Capture.png)](https://postimg.cc/image/bvwzc0qy5/)
    <br><br>
    
    ปรับขนาดความกว้างความสูง<br>
    ใช้ tag ```<img-width>ขนาดความกว้าง</img-width>```<br>
    ใช้ tag ```<img-height>ขนาดความสูง</img-height>```<br>
    ตัวอย่างเช่น กำหนดความกว้างเท่ากับ 250 และความสูงเท่ากับ 150<br>
    ```java
    table.append("<td><img>D://image.jpg</img><img-width>250</img-width><img-height>150</img-height></td>");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s33.postimg.cc/vr06u3x9b/Capture.png)](https://postimg.cc/image/ve8snxezf/)
    <br><br>
    
* **กำหนดค่าเริ่ม**<br>
    ```java
    PdfFreedom pdfFreedom = new PdfFreedom("D://", "pdf", "D://", "THSarabun", "example pdf", table.toString(), header.toString());
    // เรียกใช้ก่อน method write()
    pdfFreedom.write();
    ```
    - กำหนดกระดาษเป็นแนวนอน setHorizontal(); ตัวอย่าง ``` pdfFreedom.setHorizontal(); ```<br>
    - กำหนดระยะห่างระหว่างขอบกระดาษ setMarginDocument(36, 36, 36, 36); บน,ขวา,ล่าง,ซ้าย<br>ตัวอย่าง ``` pdfFreedom.setMarginDocument(36, 36, 36, 36); ```<br>
    หรือจะกำหนดแยก<br>
    ```java
    pdfFreedom.setMarginLeftDocument(36);
    pdfFreedom.setMarginRightDocument(36);
    pdfFreedom.setMarginTopDocument(36);
    pdfFreedom.setMarginBottomDocument(36);
    ```
    - กำหนดระยะห่างระหว่างข้อความกับขอบของคอลัมน์ setPadding(ขนาด); ตัวอย่าง  ``` pdfFreedom.setPadding(10); ``` <br>
    - กำหนดขนาดตัวอักษร setFontSize(ขนาด); ตัวอย่าง ``` pdfFreedom.setFontSize(20); ```<br>
    - กำหนดการจัดรูปแบบ setDefaultFormat(รูปแบบ); ตัวอย่าง ``` pdfFreedom.setDefaultFormat("left"); ```<br>
    - กำหนดให้แสดงจำนวนหน้า setPageNumberShow(); ต้องใส่ข้อมูลที่ header ด้วย ตัวเลขถึงจะแสดง<br>ถ้าไม่มีข้อมูลที่ header สามารถใส่เป็นค่าเปล่าๆได้ เช่น<br>
    ```java
    StringBuilder header = new StringBuilder();
    header.append("<table>");
    header.append("<tr>");
    header.append("<td><format>left</format></td>");
    header.append("</tr>");
    header.append("</table>");
    ```
    ตัวอย่าง ``` pdfFreedom.setPageNumberShow(); ```<br>
    ```java
    StringBuilder table = new StringBuilder();
    table.append("<table>");
        table.append("<tr>");
            table.append("<td>show page number</td>");
        table.append("</tr>");
    table.append("</table>");
    
    StringBuilder header = new StringBuilder();
    header.append("<table>");
        header.append("<tr>");
            header.append("<td><format>left</format></td>");
        header.append("</tr>");
    header.append("</table>");
    
    PdfFreedom pdfFreedom = new PdfFreedom("D://", "pdf", "D://", "THSarabun", "example pdf", table.toString(), header.toString());
    pdfFreedom.setPageNumberShow();
    pdfFreedom.write();
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s22.postimg.cc/hbso9qjfl/Capture.png)](https://postimg.cc/image/kvelzjm59/)
    <br><br>
    เปลี่ยนคำว่า “หน้าที่” กับ “/” ``` setPageNumberText("แทนคำว่าหน้าที่", "แทนคำว่า /"); ```<br>
    ตัวอย่าง<br>
    ```java
    pdfFreedom.setPageNumberText("page", "of");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s33.postimg.cc/den0z9ze7/Capture.png)](https://postimg.cc/image/5lwd7atez/)
    <br><br>
    เปลี่ยนตำแหน่งของการแสดงจำนวนหน้า ``` setPageNumberAlign("left or center or right"); ``` ค่าเริ่มต้นคือ right<br>
     ```java
    pdfFreedom.setPageNumberAlign("left");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s33.postimg.cc/tcvqppr4v/Capture.png)](https://postimg.cc/image/9i9p3lbx7/)
    <br><br>
     ```java
    pdfFreedom.setPageNumberAlign("center");
    ```
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s33.postimg.cc/7ez9viz5b/Capture.png)](https://postimg.cc/image/484qbwep7/)
    <br><br>
    เปลี่ยนตำแหน่งของการแสดงจำนวนหน้าไปไว้ตำแหน่งล่างสุด setPageNumberToBottom() ตัวอย่าง  ``` pdfFreedom.setPageNumberToBottom(); ``` <br>
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s22.postimg.cc/h49mqyapd/Capture.png)](https://postimg.cc/image/wczk4q4dp/)
    <br><br>
    เปลี่ยนขนาดตัวอักษร setPageNumberFontSize(ขนาด) ตัวอย่าง  ``` pdfFreedom.setPageNumberFontSize(20); ``` <br>
    ##### ผลลัพธ์ที่ได้คือ
    [![Capture.png](https://s22.postimg.cc/b2200hou9/Capture.png)](https://postimg.cc/image/ngos0tgcd/)
    <br><br>

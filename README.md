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
    ตัวที่สี่คือชื่อของไฟล์ pdf 
    ตัวที่ห้าคือข้อมูลที่จะเขียนลงในไฟล์ pdf 
    และตัวสุดท้ายคือ header ที่จะออกมาทุกแผ่นของไฟล์ จะมีหรือไม่มีก็ได้ */
    
    PdfFreedom pdfFreedom = new PdfFreedom(application, response, out, "example pdf", table.toString(), header.toString());

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

<template>
<div>
    <div class="home-index-container" style="width: 100%; height: 100%; overflow: auto">
    <el-button @click="getOnePdf" type="primary">下载单页</el-button>
    <el-button @click="getManyPdf" type="primary">下载多页</el-button>
    <!-- 转Pdf的容器 -->
    <div id="pdf-container">
      <!-- <iframe src="/Report" width="1050" height="1485">
      </iframe> -->
      <div>
        <img src="@/assets/1.png"/>
      </div>
    </div>
  </div>
</div>
</template>
<script>
import html2Canvas from "html2canvas";
import JsPDF from "jspdf";
export default({
    data() {
        
    },
    methods:{
    // 单页pdf：css高度自适应即可（此处用的一个css，为了实现多页pdf同时不让分页分割图片，css中写死了每页的高度.a4page）
    getOnePdf() {
      var title = "单页报告";
      var dom = document.getElementById("pdf-container");  // 生成pdf的html内容
      html2Canvas(dom, {
        allowTaint: true,
        scrollY: 0,
        scrollX: 0,
        useCORS: true, // 开启跨院
        width: 1000, // 这个宽度，太扯淡了，固定1000，防止偏移！
        height: dom.offsetHeight
      }).then(function(canvas) {
        var contentWidth = canvas.width;
        var contentHeight = canvas.height;
        var pdfWidth = (contentWidth + 10) / 2 * 0.75;
        var pdfHeight = (contentHeight + 200) / 2 * 0.75; // 500为底部留白
        var imgWidth = pdfWidth;
        var imgHeight = contentHeight / 2 * 0.75; //内容图片这里不需要留白的距离
        var pageData = canvas.toDataURL("image/jpeg", 1.0);
        var pdf = new JsPDF("", "pt", [pdfWidth, pdfHeight]);
        pdf.addImage(pageData, "jpeg", 0, 0, imgWidth, imgHeight);
        pdf.save(title + ".pdf");
      });
    },
    // 多页pdf-转换后的样式需要手动调整iframe的宽、高+html2Canvas的width、height+contentWidth、contentHeight
    getManyPdf() {
      let _this = this;
      var dom = document.getElementById("pdf-container"); // 生成pdf的html内容
      html2Canvas(dom, {
        allowTaint: true,
        scrollY: 0,
        scrollX: 0,
        useCORS: true, // 开启跨院
        width: 1000, // 这个宽度，太扯淡了，固定1000，防止偏移！
        height: dom.offsetHeight
      }).then(function(canvas) {
        var contentWidth = 900; // 宽高写死，强制分页
        var contentHeight = 5060;
        //一页pdf显示html页面生成的canvas高度;
        var pageHeight = contentWidth / 592.28 * 841.89;
        //未生成pdf的html页面高度
        var leftHeight = contentHeight;
        //页面偏移
        var position = 0;
        //a4纸的尺寸[595.28,841.89]，html页面生成的canvas在pdf中图片的宽高
        var imgWidth = 595.28;
        var imgHeight = 3400; // 为了分页，所以写死。592.28 / contentWidth * contentHeight会导致图片被分割
        var pageData = canvas.toDataURL("image/jpeg", 1.0);
        var pdf = new JsPDF("", "pt", "a4");
        //有两个高度需要区分，一个是html页面的实际高度，和生成pdf的页面高度(841.89)
        //当内容未超过pdf一页显示的范围，无需分页
        if (leftHeight < pageHeight) {
          pdf.addImage(pageData, "JPEG", 0, 0, imgWidth, imgHeight);
        } else {
          while (leftHeight > 0) {
            //arg3-->距离左边距;arg4-->距离上边距;arg5-->宽度;arg6-->高度
            pdf.addImage(pageData, "JPEG", 0, position, imgWidth, imgHeight);
            leftHeight -= pageHeight;
            position -= 841.89;
            //避免添加空白页
            if (leftHeight > 0) {
              pdf.addPage();
            }
          }
        }
        pdf.save(`多页报告}.pdf`);
      });
    }
    }
})
</script>
<style scoped>

</style>
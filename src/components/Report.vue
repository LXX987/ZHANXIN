<template>
<div>
    <div class="home-index-container" style="width: 100%; height: 100%; overflow: auto">
    
    <!-- 转Pdf的容器 -->
      <div id="pdf-container" style="width:1050; height:100%">
        <!-- <iframe src="/Report" width="1050" height="500">
        </iframe> -->
        <div>
          <div>用户个人信息</div>
          <div>用户资产证明</div>
          <div>信贷记录</div>
          <div>行为积累</div>
          <div>人脉圈</div>
          <div>评分等级</div>
          <div>贷款建议</div>
        </div>
      </div>
    <el-button @click="getOnePdf" type="primary">下载报告</el-button>
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
        taintTest : false,
        useCORS: true, // 开启跨域
        width: dom.offsetWidth, 
        height: dom.offsetHeight
      }).then(function(canvas) {
        var contentWidth = canvas.width;
        var contentHeight = canvas.height;
        var pdfWidth = (contentWidth + 10) / 2 * 0.75;
        var pdfHeight =contentWidth / 592.28 * 841.89;
       //未生成pdf的html页面高度
				var leftHeight = contentHeight;
        //页面偏移
					var position = 0;
       var imgWidth = 595.28;
        var imgHeight = 592.28 / contentWidth * contentHeight;
        var pageData = canvas.toDataURL("image/jpeg", 1.0);
        var pdf = new JsPDF("", "pt", [pdfWidth, pdfHeight]);
        
        if (leftHeight < pdfHeight) {
						pdf.addImage(pageData, 'jpeg', 0, 0, imgWidth, imgHeight);
					} else {
						while (leftHeight > 0) {
							pdf.addImage(pageData, 'jpeg', 0, position,
									imgWidth, imgHeight)
							leftHeight -= pdfHeight;
							position -= 841.89;
							//避免添加空白页
							if (leftHeight > 0) {
								pdf.addPage();
							}
						}
					}
        //pdf.addImage(pageData, "jpeg", 0, 0, imgWidth, imgHeight);
        pdf.save(title + ".pdf");
      });
    }   
    }
})
</script>
<style scoped>

</style>
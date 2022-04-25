<template>
  
</template>
<style scoped>

</style>
<script>
export default {
  name: 'PsychologyHistory',
  data() {
    return {

    }
  },
  mounted:function(){
    this.$axios({
            method:"post",
            url: 'http://localhost:8888/psychology/getHistory',
            params:{
            }
            }).then(res=>{
                console.log(res);
            },err=>{
                console.log(err);
            })
  }
}
</script>
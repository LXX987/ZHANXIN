<template>
    <span>啦啦啦</span>
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
    mounted(){
        this.getHistoryRecord()
    },
    methods: {
        getHistoryRecord() {
            this.$axios({
                method:"post",
                url: 'http://localhost:8888/psychology/getHistory',
                headers:{token:window.sessionStorage.getItem("token")},
                params:{
                }
            }).then(res=>{
                console.log(res);
            },err=>{
                console.log(err);
            })
        }
    }
}
</script>
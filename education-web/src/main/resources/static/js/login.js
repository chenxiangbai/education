new Vue({
    el: '#login',
    data: function() {
        return {
            ruleForm: {
                userId:'',
                password:''
            },
            rules: {
                userId: [
                    {required: true, message: '请输入账号', trigger: 'blur'}
                ],
                password: [
                    {required: true, message: '请输入密码', trigger: 'blur'}
                ]
            },
            choose: true
        }
    },
    methods: {
        location(url,timeout){
            setTimeout(function (){
                window.location.href = url;
            }, timeout);
        },
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.login()
                } else {
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        login(){
            // let res = IF.commonPostAjax("/cre-user/login",this.ruleForm,false);
            // this.$message({
            //     message: res.msg,
            //     type: res.type
            // });
            // if(res.code){
            //
            //     localStorage.setItem("role",JSON.stringify(res.map.data.role))
            //     localStorage.setItem("user",JSON.stringify(res.map.data.username))
            //     this.location("/",2000)
            // }
        }
    }
})

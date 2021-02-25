new Vue({
    el: '#index',
    data: function () {
        return {
            list: [
                //     {
                //     id: 1,
                //     title: 'JAVA中可以让线程停止执行的方法有1',
                //     options: ['sleep();', 'notify();', 'synchronized();', 'yield();'],
                //     space: '',
                //     textarea: '',
                //     textColor: '',
                //     correct: 1,
                //     description: '然而许多事件处理逻辑会更为复杂，所以直接把 JavaScript 代码写在 v-on 指令中是不可行的。因此 v-on 还可以接收一个需要调用的方法名称。',
                //     rih: -1,
                //     tyc: 'el-button--success',
                //     disable: false
                // }
                // , {
                //     id: 2,
                //     title: 'JAVA中可以让线程停止执行的方法有2',
                //     options: [],
                //     space: '1',
                //     textarea: '',
                //     textColor: '',
                //     correct: 1,
                //     description: 'test',
                //     rih: -1,
                //     tyc: 'el-button--success',
                //     disable: false
                // }
            ],
            node: 0,
            minute: 0,
            second: 0,
            timer: null,
            timeM: '00',
            timeS: '00'
        }
    },
    methods: {
        clickButton(index) {
            //this.list[this.node].disable = true;
            this.list[this.node].rih = index;
            this.set()
            this.start()
        },
        next() {
            if (this.list && this.list.length > this.node + 1) {
                // if (this.list[this.node].space != '' && this.list[this.node].space && this.list[this.node].textarea!='') {
                //     this.list[this.node].textDisable = true
                // }
                this.node++;
            } else if (this.node + 1 == this.list.length) {
                this.$message({
                    message: '提交成功',
                    type: 'success'
                });
                this.stop()
                sessionStorage.clear()
                window.location = "/margin.html"
            }
            this.set()
        },
        previous() {
            if (this.list && 0 < this.node) {
                // if (this.list[this.node].space != '' && this.list[this.node].space && this.list[this.node].textarea!='') {
                //     this.list[this.node].textDisable = true
                // }
                this.node--;
            }
            this.set()
        },
        question() {
            this.list[this.node].disable = !this.list[this.node].disable
            this.start()
            // if(this.list[this.node].textarea!=''){
            //     this.list[this.node].textDisable = true
            // }
            this.set()
        },
        set() {
            localStorage.setItem("list", JSON.stringify(this.list))
            localStorage.setItem("node", JSON.stringify(this.node))
            localStorage.setItem("minute", JSON.stringify(this.minute))
            localStorage.setItem("second", JSON.stringify(this.second))
            localStorage.setItem("timer", null)
            localStorage.setItem("timeM", JSON.stringify(this.timeM))
            localStorage.setItem("timeS", JSON.stringify(this.timeS))
        },
        init() {
            if (localStorage.getItem("list", this.list)) {
                this.list = JSON.parse(localStorage.getItem("list", this.list))
                this.node = JSON.parse(localStorage.getItem("node", this.node))
                this.minute = JSON.parse(localStorage.getItem("minute", this.minute))
                this.second = JSON.parse(localStorage.getItem("second", this.second))
                // this.timer = JSON.parse(localStorage.getItem("timer",this.timer))
                this.timeM = JSON.parse(localStorage.getItem("timeM", this.timeM))
                this.timeS = JSON.parse(localStorage.getItem("timeS", this.timeS))
            }
        },
        test() {
            if (this.list[this.node].space.trim() == this.list[this.node].textarea.trim()) {
                this.list[this.node].textColor = 'el-textarea__innerT';
            } else {
                this.list[this.node].textColor = 'el-textarea__innerX';
            }
            if (this.list[this.node].textarea.trim() == '') {
                this.list[this.node].textColor = '';
            }
            this.set()
        },
        displayTime() {
            this.second++;
            if (this.second >= 60) {
                this.minute++;
                this.second = 0;
            }
            if (this.second < 10) {
                this.timeS = '0' + this.second;
            } else {
                this.timeS = this.second;
            }
            if (this.minute < 10) {
                this.timeM = '0' + this.minute;
            } else {
                this.timeM = this.minute;
            }
            this.set()
        },
        start() {
            if (this.timer == null) {
                this.timer = window.setInterval(this.displayTime, 1000)
            }
        },
        stop() {
            if (this.timer != null) {
                clearInterval(this.timer)
                this.timer = null;
            }
            sessionStorage.clear()
        }
    },
    mounted() {
        this.init();
        var paper = sessionStorage.getItem("paper");
        if (paper) {
            var _this = this
            sessionStorage.clear()
            axios.get('/paper/' + paper).then(function (response) {
                if (!response.data.data || response.data.data.length == 0) {
                    sessionStorage.clear()
                    window.location = "margin.html"
                }
                for (var its of response.data.data) {
                    its.options = its.options.split(",")
                    its.paperName = ''
                    its.paperDescription = ''
                    its.correct = parseInt(its.correct)
                    its.rih = -1
                    its.tyc = 'el-button--success'
                    its.disable = false
                    its.textColor = ''
                }
                _this.list = response.data.data
                this.set()
            }).catch(function (error) {
            });
        }
        if (!localStorage.getItem("list") && !paper) {
            window.location = "margin.html"
        }
    }
})
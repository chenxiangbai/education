new Vue({
    el: '#margin',
    components: {
        active1: {
            data() {
                return {
                    dis: -1,
                    relevance: []
                }
            },
            template: `<el-scrollbar style="height: -moz-calc(100% - 1px) !important;
                height: -webkit-calc(100% - 1px) !important;
                height: calc(100% - 1px) !important;">
                <div class="active1" style="width: 100%;height:100%;float:left;">
                    <el-card :body-style="{ padding: '0px' }" v-for="(item,index) in relevance" :key="index" shadow="hover">
                      <div slot="header" class="clearfix">
                        <el-popover
                        popper-class="active1Item"
                        placement="bottom"
                        trigger="hover"
                        :content="item.name">
                            <div class="bottom clearfix" slot="reference">
                                <span>{{item.name}}</span>
                            </div>
                        </el-popover>
                      </div>
                      <div class="bacimg"  @mouseenter="dis = index" @mouseleave="dis = -1">
                      <img :src="dis==index?'./sj2.png':'./sj.png'" class="image myimg">
<!--                      <el-link :underline="false">-->
                          <div v-if="dis==index">
                             <el-button plain class="cover" @click="show(index)">考试</el-button>
                           </div>
<!--                      </el-link>-->
                      </div>
                      <el-divider></el-divider>
                       <el-popover
                       popper-class="active1Item"
                        placement="bottom"
                        trigger="hover"
                        :content="item.description">
                            <div class="bottom clearfix" slot="reference">
                                <span>{{item.description}}</span>
                            </div>
                        </el-popover>
                    </el-card>
                </div>
            </el-scrollbar>`,
            methods: {
                show(index) {
                    localStorage.clear();
                    sessionStorage.setItem("paper", this.relevance[index].paper)
                    window.location = "/"
                }
            },
            mounted() {
                var _this = this
                axios.get('/relevance').then(function (response) {
                    _this.relevance = response.data.data
                })
                    .catch(function (error) {

                    });
            }
        },
        active2: {
            props: ['value'],
            data() {
                return {
                    search: '',
                    data: [],
                    option: []
                }
            },
            template: `
               <el-table
               :data="data"
                height="100%"
                style="width: 100%">
                <el-table-column
                  label="题目"
                  prop="title">
                </el-table-column>
                <el-table-column
                  align="right">
                  <template slot="header" slot-scope="scope">
                      <el-select size="mini" v-model="search" placeholder="请选择试卷" @change="change">
                        <el-option
                          v-for="item in option"
                          :key="item.name"
                          :label="item.name"
                          :value="item.paper">
                        </el-option>
                      </el-select>
                  </template>
                  <template slot-scope="scope">
                    <el-button
                      size="mini"
                      @click="edit(scope.$index, scope.row)">编辑</el-button>
                    <el-button
                      size="mini"
                      type="danger"
                      @click="del(scope.$index, scope.row)">删除</el-button>
                  </template>
                </el-table-column>
              </el-table>`,
            methods: {
                change(value) {
                    var _this = this
                    axios.get('/paper/' + value).then(function (response) {
                        _this.data.splice(0, _this.data.length);
                        _this.data = response.data.data
                    }).catch(function (error) {
                    });

                },
                edit(index, row) {
                    // this.data.push({
                    //     title: '王小虎'
                    // })
                },
                del(index, row) {
                    var _this = this
                    var list = [row];
                    axios.put('/paper/' + _this.search, list).then(function (response) {
                        _this.data.splice(index, 1);
                        _this.$message({
                            message: response.data.msg,
                            type: response.data.code
                        });
                    }).catch(function (error) {
                    });
                }
            },
            mounted() {
                var _this = this
                axios.get('/relevance').then(function (response) {
                    _this.option = response.data.data
                }).catch(function (error) {
                });

            }
        },
        active3: {
            props: ['data', 'del', 'edit'],
            data() {
                return {
                    search: '',
                    value: '',
                    option: [],
                    list: [{
                        id: 1,
                        title: 'JAVA中可以让线程停止执行的方法有1',
                        option: ['sleep();', 'notify();', 'synchronized();', 'yield();'],
                        space: '',
                        correct: 1,
                        description: '然而许多事件处理逻辑会更为复杂，所以直接把 JavaScript 代码写在 v-on 指令中是不可行的。因此 v-on 还可以接收一个需要调用的方法名称。',
                        radio: '1',
                        paperName: '',
                        paperDescription: ''
                    }]
                }
            },
            template: `
            <el-scrollbar style="height: -moz-calc(100% - 1px) !important;
                height: -webkit-calc(100% - 1px) !important;
                height: calc(100% - 1px) !important;">
                <div style="margin-bottom: 230px;">
                <div style="margin-top: 10px;padding:0px 20px;width: -moz-calc(100% - 80px) !important;width: -webkit-calc(100% - 80px) !important;width:calc(100% - 80px) !important;"> 
                   <el-autocomplete
                      class="inline-input"
                      style="margin-bottom: 20px;"
                      v-model="search"
                      size="small"
                      :fetch-suggestions="querySearch"
                      placeholder="输入试卷名"
                      @select="handleSelect"
                    ></el-autocomplete>
                    <el-link type="danger" style="font-size: 25px;" :underline="false" @click="delRelevance"><i class="el-icon-close"></i> </el-link>
                </div>
                <div v-for="(ob,ins) in list" :key="ins" style="margin-bottom:40px;margin-top: 10px;padding:0px 20px;width: -moz-calc(100% - 80px) !important;width: -webkit-calc(100% - 80px) !important;width:calc(100% - 80px) !important;">
<!--                  <el-select style="margin-bottom: 20px;width: -moz-calc(100% - 60px) !important;width: -webkit-calc(100% - 60px) !important;width:calc(100% - 60px) !important;" size="mini" v-model="search" filterable placeholder="选择修改的试卷名" @change="change">-->
<!--                     <el-option-->
<!--                       v-for="item in option"-->
<!--                       :key="item.name"-->
<!--                       :label="item.name"-->
<!--                       :value="item.paper">-->
<!--                     </el-option>-->
<!--                   </el-select>-->

                   <div>
                       <el-radio v-model="ob.radio" label="1">选择题</el-radio>
                       <el-radio v-model="ob.radio" label="2">填空题</el-radio>
                       <el-popover
                          placement="bottom"
                          width="400"
                          :disabled="ob.radio==2"
                          trigger="click">
                          <el-radio-group v-model="ob.correct" style="padding:0px 20px;padding-top: 20px;">
                            <el-radio v-for="(item,index) in ob.option" :label="index" style="margin-bottom: 20px;">选项-{{index}}</el-radio>
                          </el-radio-group>
                       <el-link icon="el-icon-aim" slot="reference" style="top:-2px;margin-left: 10px;" :underline="false" :disabled="ob.radio==2">设置答案</el-link>
                      </el-popover>
                       
                      <el-popover
                          placement="bottom"
                          width="680"
                          :disabled="ob.radio==1"
                          trigger="click">
                          <el-input
                          type="textarea"
                          placeholder="请输入详细解释"
                          v-model="ob.description">
                          </el-input>
                       <el-link icon="el-icon-edit" slot="reference" style="top:-2px;margin-left: 30px;" :underline="false" :disabled="ob.radio==1">详解</el-link>
                      </el-popover>
                   </div>
                   <div style="margin-top:20px;">
                       <el-input
                          style="width:calc(100% - 60px) !important;width:-moz-calc(100% - 60px) !important;width:-webkit-calc(100% - 60px) !important;"
                          type="textarea"
                          autosize
                          placeholder="请输入题目"
                          v-model="ob.title">
                       </el-input>
                       <span v-if="ob.radio=='1'">
                         <el-link type="danger" style="font-size: 25px;" :underline="false" @click="subR(ins)"><i class="el-icon-close"></i> </el-link>
                         <el-link type="success" style="font-size: 25px;" :underline="false" @click="add(ins)"><i class="el-icon-plus"></i> </el-link>
                       </span>

<!--                       <el-link type="danger" style="font-size: 20px;top:-1px;float: right;" :underline="false"><i class="el-icon-delete"></i> </el-link>-->
                   </div>
                   <span v-if="ob.radio=='2'" class="textA">
                        <el-input
                          style="margin-top:20px;width:calc(100% - 60px) !important;width:-moz-calc(100% - 60px) !important;width:-webkit-calc(100% - 60px) !important;"
                          type="textarea"
                          placeholder="请输入答案"
                          v-model="ob.space">
                       </el-input>
                   </span>
                   <span v-else v-for="(item,index) in ob.option" :key="index">
                       <el-input
                          style="margin-top:20px;width:calc(100% - 60px) !important;width:-moz-calc(100% - 60px) !important;width:-webkit-calc(100% - 60px) !important;"
                          type="textarea"
                          autosize
                          placeholder="请输入选项内容"
                          v-model="ob.option[index]">
                       </el-input>
                       <span style="top:-5px;" >
                           <el-link type="danger" style="font-size: 25px;" :underline="false" @click="sub(ins,index)"><i class="el-icon-close"></i> </el-link>
                       </span>
                   </span>
                </div>
                <div style="position:absolute;border-bottom:1px solid #e6e6e6;padding-left:20px;bottom:0px;margin-bottom:0px;height:60px;padding-top:20px;width:100%;background-color: white;">
                       <el-button type="primary" @click="addList">新增</el-button>
                       <el-button type="success" @click="saveList">保存</el-button>
                       <el-button type="danger" @click="delList">删除</el-button>
                </div>
                </div>
            </el-scrollbar>
            `,
            methods: {
                add(index) {
                    this.list[index].option.push('')
                },
                sub(ins, index) {
                    this.list[ins].option.splice(index, 1)
                },
                subR(index) {
                    this.list.splice(index, 1)
                },
                addList() {
                    var size = 4;
                    if (this.list[this.list.length - 1]) {
                        size = this.list[this.list.length - 1].option.length
                    }
                    this.list.push({
                        id: this.list.length + 1,
                        title: '',
                        option: new Array(4),
                        space: '',
                        correct: 1,
                        description: '',
                        radio: '1',
                        paperName: '',
                        paperDescription: ''
                    })

                },
                delList() {
                    if (this.list.length > 0) {
                        this.list.splice(this.list.length - 1, 1)
                    }
                },
                saveList() {
                    var _this = this
                    for (var l of _this.list) {
                        l.options = l.option.join(",")
                        l.paperName = _this.search;
                    }
                    axios.post('/paper/' + _this.value, _this.list).then(function (response) {
                        console.log(response.data.code)
                        _this.$message({
                            message: response.data.msg,
                            type: response.data.code
                        });
                    }).catch(function (error) {
                    });



                },
                querySearch(queryString, cb) {
                    var results = queryString ? this.option.filter(this.createFilter(queryString)) : this.option;
                    // 调用 callback 返回建议列表的数据
                    cb(results);
                },
                createFilter(queryString) {
                    return (restaurant) => {
                        return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
                    };
                },
                handleSelect(item) {
                    this.value = item.paper
                    var _this = this
                    axios.get('/paper/' + this.value).then(function (response) {
                        for (var its of response.data.data) {
                            its.option = its.options.split(",")
                            its.radio = its.space == '' ? '1' : '2'
                            its.paperName = ''
                            its.paperDescription = ''
                            its.correct = parseInt(its.correct)
                        }
                        _this.list = response.data.data
                    }).catch(function (error) {
                    });
                },
                delRelevance() {
                    if (this.value != '') {
                        var _this = this
                        axios.delete('/paper/' + _this.value,{}).then(function (response) {
                            var index = _this.option.filter((key, val) => {
                                if (key.value == _this.value) {
                                    return val;
                                }
                            })
                            delete _this.option[index]
                            _this.search = ''
                            _this.value = ''
                            _this.$message({
                                message: response.data.msg,
                                type: response.data.code
                            });
                        }).catch(function (error) {
                        });
                    }
                }
            },
            mounted() {
                var _this = this
                axios.get('/relevance').then(function (response) {
                    for (var item of response.data.data) {
                        delete item.description
                        item.value = item.name
                        delete item.name
                    }
                    _this.option = response.data.data;
                }).catch(function (error) {
                });
            }
        },
        active4: {
            template: `<h1></h1>`
        }
    },
    data: function () {
        return {
            isCollapse: true,
            active: '3',
            templateActives: {'1': 'active1', '2': 'active2', '3': 'active3', '4': 'active4'},
            titleName: {'1': '试卷考试', '2': '题目管理', '3': '试卷添加', '4': '个人设置'},
            values: ''
        }
    },
    methods: {
        handleOpen(key, keyPath) {
            this.active = new String(key)
        }
    },
    mounted() {
        localStorage.clear();

        // var socket;
        // if (typeof (WebSocket) == "undefined") {
        //     console.log("遗憾：您的浏览器不支持WebSocket");
        // } else {
        //     console.log("恭喜：您的浏览器支持WebSocket");
        //     //实现化WebSocket对象
        //     //指定要连接的服务器地址与端口建立连接
        //     //注意ws、wss使用不同的端口。我使用自签名的证书测试，
        //     //无法使用wss，浏览器打开WebSocket时报错
        //     //ws对应http、wss对应https。
        //     socket = new WebSocket("ws://localhost:8085/ws/asset");
        //     //连接打开事件
        //     socket.onopen = function () {
        //         console.log("Socket 已打开");
        //         var data = {"token": "test"}
        //         socket.send(JSON.stringify(data));
        //     };
        //     //收到消息事件
        //     socket.onmessage = function (msg) {
        //         console.log(msg.data);
        //     };
        //     //连接关闭事件
        //     socket.onclose = function () {
        //         console.log("Socket已关闭");
        //     };
        //     //发生了错误事件
        //     socket.onerror = function () {
        //         alert("Socket发生了错误");
        //     }
        //     //窗口关闭时，关闭连接
        //     window.unload = function () {
        //         socket.close();
        //     };
        // }
    }
})

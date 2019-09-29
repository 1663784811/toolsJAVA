<template>
    <div class="currencyTabBox">
        <div class="mainDataBox">
            <!--===========     基础操作     ==============-->
            <BaseOperation
                    :searchWhere="searchWhere"
                    @clickAddBtn="clickAddBtn"
                    @clickUpdateBtn="clickUpdateBtn"
                    @clickDelBtn="clickDelBtn"
                    @clickSearchBtn="clickSearchBtn"
                    @changeSearchBtn="changeSearchBtn"
            ></BaseOperation>
            <!--===========    表格数据     ==============-->
            <div class="tableBox scroll">
                <Table size="small" ref="pageTable"
                       :columns="TableHeader"
                       :data="tableData"
                       :loading="tableLoading"
                       @on-selection-change="selectionChange"
                       @on-current-change="selectionChangeOne"
                       highlight-row
                ></Table>
            </div>
            <!--===========    分页     ==============-->
            <div class="pageBox">
                <Page show-total
                      show-elevator
                      show-sizer
                      :total="total"
                      :page-size="requestTableData.size"
                      @on-change="clickChangePage"
                      @on-page-size-change="clickChangeSize"
                />
            </div>
        </div>
        <div class="rightDataBox">
            <BasePanel title="角色">
                <BaseList @clickDelBtn="baseListDelClickBtn" ref="rightBaseList"></BaseList>
            </BasePanel>
        </div>
        <!--===========    操作弹出层     ==============-->
        <!--====  添加  ====-->
        <BaseWindow ref="refAddBaseWindow"
                    :title="addTitle"
                    :fieldData="addFieldData"
                    @submitForm="addSubmitForm">
        </BaseWindow>
        <!--====  修改  ====-->
        <BaseWindow ref="refUpdateBaseWindow"
                    :title="updateTitle"
                    :fieldData="updateFieldData"
                    @submitForm="updateSubmitForm">
        </BaseWindow>
        <!--======   删除提示   ======-->
        <Modal v-model="isDelInfo" width="360">
            <p slot="header" style="color:#f60;text-align:center">
                <Icon type="ios-information-circle"></Icon>
                <span>刪除数据</span>
            </p>
            <div style="text-align:center">
                <p>是否要刪除?</p>
            </div>
            <div slot="footer">
                <Button type="error" size="large" long :loading="isDelLoading" @click="delData" >刪除</Button>
            </div>
        </Modal>

    </div>
</template>

<script>
    import BaseOperation from '../../components/admin/BaseOperation';
    import BaseWindow from '../../components/admin/BaseWindow';
    import BasePanel from '../../components/admin/BasePanel';
    import BaseList from '../../components/admin/BaseList';


    export default {
        name: "${operationTools.indexToUpperCase(tableName)}BaseRightList",
        components: {
            BaseOperation,
            BaseWindow,
            BasePanel,
            BaseList
        },
        data() {
            return {
                //=====================================================    请求参数
                requestTableData: {//请求表格数据参数
                    page: 1,
                    size: 30,
                    sort: "${operationTools.allToLowerCase(primarykey)}_desc"
                },
                submitData: {},//添加修改提交数据,
                //=====================================================     响应数据
                //==========表格START
                tableLoading: true,
                TableHeader: [],         //表头
                tableData: [],      //数据
                total: 0,       //总数
                selectData: [],      //已经选择的数据
                //==========表格END

                //==========操作 START
                addFieldData: [],//添加字段数据
                addTitle: "新增",

                updateFieldData: [],//修改字段数据
                updateTitle: "修改",
                //========== 操作 END
                searchWhere: [],
                //==========  删除
                isDelInfo:false,
                isDelLoading:false
            }
        },
        created() {
            //====   表头
            this.ajaxGet("${__Table__}_field.json", {}).then(responseData => {
                this.addTitle = responseData.title;
                this.addFieldData = responseData.fieldList;
                this.updateTitle = responseData.title;
                this.updateFieldData = responseData.fieldList;
                for (let i = 0; i < this.addFieldData.length; i++) {
                    if (this.addFieldData[i].isWhere) {
                        let json = {};
                        json.keyStr = this.addFieldData[i].javaWhere + '_' + this.addFieldData[i].javaType + '_' + this.addFieldData[i].key;
                        json.name = this.addFieldData[i].title;
                        this.searchWhere.push(json)
                    }
                    if (this.addFieldData[i].isShowColumn) {
                        this.TableHeader.push(this.addFieldData[i])
                    }
                }
            });
            //====  数据
            this.requestTableDataFn();
        },
        methods: {
            //多选
            selectionChange: function (data) {
                this.selectData = data;
            },
            //单选
            selectionChangeOne: function (currentRow) {
            },
            //=================================================== 操作  START
            //点击添加
            clickAddBtn: function () {
                this.$refs.refAddBaseWindow.operationWindow(true);
            },
            //点击修改
            clickUpdateBtn: function () {
                let that = this;
                let selectData = this.selectData;
                if (selectData.length == 1) {
                    this.findIdFn({id: selectData[0].id}, function () {
                        that.$refs.refUpdateBaseWindow.operationWindow(true);
                    })
                } else if (selectData.length > 1) {
                    this.$Message.info("请选择一条数据");
                } else {
                    this.$Message.info("请选择数据");
                }
            },
            //点击删除
            clickDelBtn: function () {
                let selectData = this.selectData;
                if (selectData.length > 0) {
                    this.isDelInfo=true;
                } else {
                    this.$Message.info("请选择数据");
                }
            },
            //点击搜索
            clickSearchBtn: function () {
                this.requestTableDataFn();
            },
            //执行删除
            delData:function(){
                this.isDelLoading=true;
                let selectData = this.selectData;
                let idArr = [];
                for (let i = 0; i < selectData.length; i++) {
                    idArr.push(selectData[i].id);
                }
                this.delIdFn({idArr: idArr});
            },
            //点击搜索
            clickSearchBtn: function () {
                this.requestTableDataFn();
            },
            //改变搜索条件
            changeSearchBtn: function (obj) {
                if (obj.selectValue && obj.selectValue != "") {
                    this.requestTableData.jsonStr = '{' + obj.selectValue + ':"' + obj.searchData + '"}';
                } else {
                    this.requestTableData.jsonStr = "";
                }
            },
            //=================================================== 操作  END
            //=== 添加提交表单数据
            addSubmitForm: function (formData) {
                this.submitData = formData;
                this.addSubmitDataFn();
            },
            //=== 修改提交表单数据
            updateSubmitForm: function (formData) {
                this.submitData = formData;
                this.updateSubmitDataFn();
            },
            //===== 分页
            clickChangePage: function (page) {
                this.requestTableData.page = page;
                this.requestTableDataFn();
            },
            clickChangeSize: function (size) {
                this.requestTableData.size = size;
                this.requestTableDataFn();
            },
            //=========================================================================================================================  右边组件
            baseListDelClickBtn: function (data) {
                getRightBaseList();
            },
            //=========================================================================================================================  右边组件
            //======================================================================   请求数据
            //表格数据
            requestTableDataFn: function (successCallBack, errorCallBack) {
                this.tableData = [];
                this.selectData = [];
                this.tableLoading = true;
                this.ajaxGet("findPage${operationTools.indexToUpperCase(tableName)}", this.requestTableData, 1).then(responseData => {
                    this.tableData = responseData.data;
                this.total = responseData.total;
                successCallBack ? successCallBack() : "";
                this.tableLoading = false;
            }, errorData => {
                    this.$Message.error("查询数据失败"+errorData);
                    this.tableLoading = false;
                    errorCallBack ? errorCallBack(errorData) : "";
                });
            },
            //新增数据请求
            addSubmitDataFn: function (successCallBack, errorCallBack) {
                this.ajaxPost("add${operationTools.indexToUpperCase(tableName)}", this.submitData, 1).then(() => {
                    this.$refs.refAddBaseWindow.operationWindow(false);
                this.$refs.refAddBaseWindow.setfromData({});
                this.$Message.success("添加成功");
                this.requestTableDataFn();
                successCallBack ? successCallBack() : "";
            }, errorData => {
                    this.$Message.error("添加失败"+errorData);
                    errorCallBack ? errorCallBack(errorData) : "";
                });
            },
            //修改数据请求
            updateSubmitDataFn: function (successCallBack, errorCallBack) {
                this.ajaxPost("update${operationTools.indexToUpperCase(tableName)}", this.submitData, 1).then(() => {
                    this.$refs.refUpdateBaseWindow.operationWindow(false);
                this.$refs.refUpdateBaseWindow.setfromData({});
                this.$Message.success("修改成功");
                this.requestTableDataFn();
                successCallBack ? successCallBack() : "";
            }, errorData => {
                    this.$Message.error("修改失败");
                    errorCallBack ? errorCallBack(errorData) : "";
                });
            },
            //根ID查找数据
            findIdFn: function (sendObj, successCallBack, errorCallBack) {
                this.ajaxGet("findId${operationTools.indexToUpperCase(tableName)}", sendObj, 1).then(responseData => {
                    this.$refs.refUpdateBaseWindow.setfromData(responseData);
                successCallBack ? successCallBack(responseData) : "";
            }, errorData => {
                    this.$Message.error("查询数据失败"+errorData);
                    errorCallBack ? errorCallBack(errorData) : "";
                });
            },
            //根ID删除数据
            delIdFn: function (sendObj, successCallBack, errorCallBack) {
                this.ajaxGet("del${operationTools.indexToUpperCase(tableName)}", sendObj, 1).then(responseData => {
                    this.$Message.success(responseData.message);
                this.isDelInfo=false;
                this.isDelLoading=false;
                this.requestTableDataFn();
                successCallBack ? successCallBack(responseData) : "";
            }, errorData => {
                    this.isDelLoading=false;
                    this.$Message.error("刪除失败"+errorData);
                    errorCallBack ? errorCallBack(errorData) : "";
                });
            },
            //获取右边数据
            getRightBaseList: function (sendObj, successCallBack, errorCallBack) {
                this.$refs.rightBaseList.setListData([]);
                this.ajaxGet("findAllTRole", sendObj, 1).then(responseData => {
                    this.$refs.rightBaseList.setListData(responseData);
                    successCallBack ? successCallBack(responseData) : "";
                }, errorData => {
                    errorCallBack ? errorCallBack(errorData) : "";
                });
            }
        }


    }
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
    .currencyTabBox
        padding 0px;
        height 100%
        overflow hidden;
        position: relative;

        .mainDataBox
            height: 100%;
            margin-right 200px;

            .tableBox
                padding: 0 10px 10px 10px
                height calc(100% - 96px)
                overflow auto

            .pageBox
                width: 100%;
                min-height: 50px;
                text-align right;
                padding: 10px;
                border-top: 1px solid #eee;
                background: #fff;
                z-index: 10

        .rightDataBox
            position: absolute;
            right: 0;
            top: 0;
            bottom: 0;
            overflow: hidden;
            background: #fff;
            width: 200px;
            border-left: 1px solid #eee;
</style>

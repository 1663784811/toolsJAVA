<template>
    <div class="page">
        <div class="pageLeft">
            <div class="searchbox">
                <Input search placeholder="搜索"/>
            </div>
            <div class="btnbox">
                <Button type="success" size="small" @click="addPage">添加</Button>
            </div>
            <div class="treeBox">
                <TreeNode :treeData="pageArr" :theme="'normal'"></TreeNode>
            </div>
        </div>
        <div class="componentBox">
            <div class="comBox">
                <p class="iconCom">
                    <Icon type="md-arrow-dropdown-circle"/>
                </p>
                <p class="iconText textNowrap">基础</p>
            </div>
            <div class="comBox">
                <p class="iconCom">
                    <Icon type="md-arrow-dropdown-circle"/>
                </p>
                <p class="iconText textNowrap">基础</p>
            </div>
            <div class="comBox">
                <p class="iconCom">
                    <Icon type="md-arrow-dropdown-circle"/>
                </p>
                <p class="iconText textNowrap">基础</p>
            </div>
            <div class="comBox">
                <p class="iconCom">
                    <Icon type="md-arrow-dropdown-circle"/>
                </p>
                <p class="iconText textNowrap">基础</p>
            </div>
            <div class="comBox">
                <p class="iconCom">
                    <Icon type="md-arrow-dropdown-circle"/>
                </p>
                <p class="iconText textNowrap">基础</p>
            </div>
            <div class="comBox">
                <p class="iconCom">
                    <Icon type="md-arrow-dropdown-circle"/>
                </p>
                <p class="iconText textNowrap">基础</p>
            </div>
            <div class="comBox">
                <p class="iconCom">
                    <Icon type="md-arrow-dropdown-circle"/>
                </p>
                <p class="iconText textNowrap">基础</p>
            </div>
        </div>
        <div class="mainbox">
            <div class="pageType">
                <RadioGroup type="button" size="small">
                    <Radio label="PC"></Radio>
                    <Radio label="移动"></Radio>
                </RadioGroup>
            </div>
            <div class="pageMain">
                <div class="pageRow clear_f"
                     v-for="(item,index) in templateArr"
                     :key="index"
                     :class="{active:item === selectRow}"
                     @click="clickRow(item)"
                >
                    <div v-html="item.html"></div>
                    <Icon class="closeicon" type="md-close-circle"/>
                    <div class="rowdiv"></div>
                </div>
            </div>
        </div>
        <div class="pageRight">
            <div>
                <Divider orientation="left" size="small">接口</Divider>
                <Select v-model="model8" clearable>
                    <Option v-for="item in cityList" :value="item.value" :key="item.value">{{ item.label }}</Option>
                </Select>
                <Divider orientation="left" size="small">内容</Divider>
                <div>
                    <div><Input v-model="value" placeholder="内容"/></div>
                    <div><Input v-model="value" placeholder="内容"/></div>
                    <div><Input v-model="value" placeholder="内容"/></div>
                    <div><Input v-model="value" placeholder="内容"/></div>
                </div>
                <div>
                    ddd
                </div>
            </div>
        </div>


        <!--===========    操作弹出层     ==============-->
        <BaseWindow ref="winRef"
                    :show.sync="winShow"
                    :title="winTitle"
                    :fieldData="winFieldData"
                    :valueData="winValueData"
                    :saveLoadding="winLoadding"
                    @submitForm="submitForm"
        ></BaseWindow>


    </div>
</template>
<script>
    import TreeNode from '@/components/tree/TreeNode';
    import BaseWindow from '@/components/BaseWindow';

    export default {
        name: "Page",
        components: {
            TreeNode,
            BaseWindow
        },
        data() {
            return {
                winShow: false,
                winTitle: "添加页面",
                winFieldData: [],
                winValueData: {},
                winLoadding: false,
                templateArr: [
                    {
                        html: "<div>ddddssss</div>"
                    }
                ],
                pageArr: [
                    {
                        "tid": "1",
                        "name": "配置系统",
                        "iconType": "logo-tux",
                        "isOpenTree": true
                    }
                ]
            }
        },
        methods: {
            /**
             * 选择组件
             */
            clickRow: function (obj) {
                this.selectRow = obj;
            },
            /**
             * 添加页面
             */
            addPage: function () {
                this.winShow = true;
            },
            submitForm: function () {

            }
        }

    }
</script>

<style scoped lang="stylus">
    .pageLeft
        position: fixed;
        top: 0;
        left: 0;
        bottom: 0;
        background: #f3f3f3;
        width: 200px;
        padding: 4px;

        .btnbox
            margin: 6px 0;


    .componentBox
        position: fixed;
        top: 0;
        left: 200px;
        bottom: 0;
        padding: 4px;
        width: 200px;
        background: #fff;
        border-left: 1px solid #e8e8e8;
        border-right: 1px solid #e8e8e8;
        display: flex;
        flex-wrap: wrap;
        align-content: flex-start;
        justify-content: space-around;

        .comBox
            text-align: center;
            width: 60px;
            height: 60px;
            cursor: pointer;
            background: #f9f9f9;
            margin: 3px 0;
            display: flex;
            flex-wrap: wrap;
            align-items: center;
            justify-content: center;
            user-select: none;

            &:hover
                background: #eee;

            .iconCom
                width: 100%;
                font-size: 12px;

    .mainbox
        margin-left: 400px;
        margin-right: 200px;

        .pageType
            text-align: center;
            padding: 4px;

        .pageMain
            margin: 0 10px;
            padding: 4px;
            min-height: 500px;
            border: 1px dashed #b1afaf;

            .pageRow
                border: 1px dashed #b1afaf;
                position: relative;
                padding: 3px;
                margin: 3px 0;

                &.active
                    border: 1px dashed #f88;

                &:hover
                    .closeicon
                        display: block;

                .closeicon
                    position: absolute;
                    top: -8px;
                    right: -8px;
                    z-index: 2
                    font-size: 16px;
                    cursor: pointer;
                    display: none;

                    &:hover
                        color: #19be6b;

                .rowdiv
                    position: absolute;
                    top: 0;
                    left: 0;
                    right: 0;
                    bottom: 0;
                    background: rgba(255, 255, 255, 0);
                    z-index: 1;
                    cursor: move;

    .pageRight
        position: fixed;
        top: 0;
        bottom: 0;
        right: 0;
        width: 200px;
        background: #fbfbfb;
        border-left: 1px solid #e8e8e8;
        padding: 4px;
</style>
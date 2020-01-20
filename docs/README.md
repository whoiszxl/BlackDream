# Java公链与SpringCloud交易所项目
[![MyWebSite](https://img.shields.io/badge/我的站点-whoiszxl-blue.svg)](https://whoiszxl.github.io)
[![docs](https://img.shields.io/badge/docs-reference-green.svg)](https://whoiszxl.github.io)
[![teach](https://img.shields.io/badge/教程-BohemianRhapsody-orange.svg)](https://github.com/whoiszxl/BohemianRhapsody)
[![email](https://img.shields.io/badge/email-whoiszxl@gmail.com-red.svg)](https://whoiszxl.github.io)


### 项目介绍
通过Java Springboot开发一个简易版公链项目，并实现钱包功能，再通过SpringCloud开发一套中心化交易所项目，考虑再用Flutter实现移动端的APP，API则通过[yapi][yapi]进行管理。

### 项目文档
文档地址： http://whoiszxl.com

### 项目架构
```
xexchange
├── xexchange-core            -- 基础核心代码
├── xexchange-oauth           -- oauth2认证代码
├── xexchange-eureka          -- eureka注册中心
├── xexchange-gateway         -- 网关
├── xexchange-manager         -- 管理后台代码
├── xexchange-chat            -- 聊天模块
├── xexchange-trade           -- 交易模块
├── xexchange-market          -- 行情socket服务
├── xexchange-usercenter      -- 用户中心服务
├── xexchange-wallet          -- 钱包监控管理服务
├── xexchange-openapi         -- 开放api服务
├── xexchange-otc             -- OTC服务
├── xexchange-fescar          -- 分布式事务服务
├── xexchange-sms             -- SMS短信发送服务


xwallet
├── xwallet-eth               -- eth钱包
├── xwallet-erc20             -- erc20代币钱包
├── xwallet-btc               -- btc钱包

doc                           -- 相关文档
xchain                        -- 一个简单的公链项目
xexchange-flutter             -- xexchange Flutter App

xexchange-front-admin         -- Vue Admin管理后台
xexchange-front-web           -- Vue 用户端
```


##### 公众号
![公众号](https://oss.whoiszxl.com/qrcode_for_whoisc137_258.jpg)
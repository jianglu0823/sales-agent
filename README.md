# 智能销售数据分析 Agent (Sales Agent)

一个基于 AI 的智能销售数据分析助手，通过自然语言对话帮助销售团队快速查询、分析和可视化销售数据。

## 项目概述

本项目是一个智能销售数据分析系统，结合大语言模型（LLM）与企业销售数据，让用户可以通过自然语言对话的方式快速获取销售洞察。系统支持销售数据查询、趋势分析、异常检测、图表生成等功能。

## 技术栈

### 后端 (sales-agent/)

| 技术 | 版本 | 用途 |
|------|------|------|
| **Java** | 21 | 开发语言 |
| **Spring Boot** | 3.5.11 | 应用框架 |
| **LangChain4j** | 1.12.1 | AI Agent 框架 |
| **通义千问 (DashScope)** | - | 大语言模型 (OpenAI 兼容接口) |
| **Spring Data JPA** | - | ORM 框架 |
| **MySQL** | - | 关系型数据库 |
| **Redis** | - | 缓存与会话存储 |
| **Sa-Token** | 1.39.0 | 权限认证框架 |
| **Lombok** | - | 代码简化 |
| **Maven** | - | 构建工具 |

### 前端 (sales-agent-front/)

| 技术 | 版本 | 用途 |
|------|------|------|
| **Vue.js** | 3.5.13 | 前端框架 |
| **Vite** | 6.1.0 | 构建工具 |
| **Element Plus** | 2.9.7 | UI 组件库 |
| **Pinia** | 2.3.0 | 状态管理 |
| **Vue Router** | 4.5.0 | 路由管理 |
| **ECharts** | 5.5.1 | 图表库 |
| **Axios** | 1.7.9 | HTTP 客户端 |
| **Markdown-it** | 14.1.0 | Markdown 渲染 |

## 核心功能

### 1. 智能对话分析
- 支持自然语言查询销售数据
- 多轮对话保持上下文记忆（MySQL 持久化）
- 流式输出响应（SSE）

### 2. 销售数据查询工具
- **订单查询**: 按时间范围、大区、销售员筛选订单
- **销售汇总**: 统计总额、排名、Top N 销售员/产品
- **趋势分析**: 同比/环比分析、月度趋势
- **图表生成**: 自动生成 ECharts 格式的折线图、柱状图、饼图
- **异常检测**: 自动识别销售数据异常（订单暴跌、断货预警、业绩断崖等）

### 3. 数据模型
- **销售大区**: 华东区、华南区、华北区、西南区
- **销售员**: 支持经理、销售代表、总监等角色
- **产品**: SKU 管理、品类分类、成本与售价
- **销售订单**: 完整的订单生命周期管理

### 4. 安全与权限
- 基于 Sa-Token 的登录认证
- 工具输入参数校验
- 用户上下文隔离

## 项目结构

```
sales-agent/
├── sales-agent/                    # 后端服务
│   ├── src/main/java/com/mk/salesAgent/
│   │   ├── agent/                  # AI Agent 接口定义
│   │   ├── config/                 # 配置类（Redis、WebMvc、异常处理等）
│   │   ├── controller/             # REST API 控制器
│   │   ├── dto/                    # 数据传输对象
│   │   ├── entity/                 # JPA 实体类
│   │   ├── memory/                 # 对话记忆持久化
│   │   ├── repository/             # 数据访问层
│   │   ├── security/               # 安全相关
│   │   ├── service/                # 业务逻辑层
│   │   └── tool/                   # AI 工具类（@Tool 注解）
│   └── src/main/resources/
│       ├── db/                     # 数据库脚本（schema.sql、data.sql）
│       └── application.yml         # 应用配置
│
├── sales-agent-front/              # 前端应用
│   ├── src/
│   │   ├── api/                    # API 接口封装
│   │   ├── components/             # Vue 组件（消息气泡、图表渲染）
│   │   ├── router/                 # 路由配置
│   │   ├── stores/                 # Pinia 状态管理
│   │   ├── views/                  # 页面视图（聊天页、登录页）
│   │   └── assets/                 # 静态资源
│   └── package.json
│
└── README.md
```

## 快速开始

### 环境要求
- JDK 21+
- MySQL 8.0+
- Redis 6.0+
- Node.js 18+

### 后端启动

1. 配置数据库和 Redis（修改 `application.yml`）
2. 设置环境变量 `DASH_SCOPE_API_KEY`（通义千问 API 密钥）
3. 运行 SQL 脚本初始化数据
4. 启动 Spring Boot 应用

```bash
cd sales-agent
mvn spring-boot:run
```

### 前端启动

```bash
cd sales-agent-front
npm install
npm run dev
```

### 默认访问
- 前端: http://localhost:5173
- 后端 API: http://localhost:8087

## 使用示例

用户可以通过自然语言与系统交互：

- "查询本月华东区的销售总额"
- "帮我生成近6个月的销售趋势图"
- "谁是本季度业绩最好的销售员"
- "检测最近有什么销售异常"
- "对比今年和去年的销售额"

## 特色亮点

1. **AI 驱动**: 基于 LangChain4j 构建，集成通义千问大模型
2. **工具化设计**: 使用 `@Tool` 注解将业务功能暴露给 AI
3. **数据可视化**: 自动生成 ECharts 图表，前端实时渲染
4. **对话记忆**: 支持多轮对话，上下文持久化到 MySQL
5. **异常预警**: 内置多种异常检测规则，主动发现业务问题
6. **流式响应**: 支持 SSE 流式输出，提升用户体验

## 许可证

MIT License

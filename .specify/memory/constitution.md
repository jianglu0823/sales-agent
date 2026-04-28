# 【核心】项目宪法：Sales Agent

## 1. 项目概述

智能销售数据分析 Agent，通过自然语言对话帮助销售团队快速查询、分析和可视化销售数据。

**项目愿景**：让销售数据触手可及，用 AI 赋能销售决策。

**核心价值**：
- 自然语言交互，降低数据查询门槛
- 智能分析，自动发现业务洞察
- 可视化呈现，直观展示数据

---

## 2. 技术栈

### 2.1 后端技术栈

| 分类 | 技术 | 版本 | 选型理由 |
|------|------|------|----------|
| 语言 | Java | 21 | LTS 版本，性能稳定，生态成熟 |
| 框架 | Spring Boot | 3.5.11 | 社区成熟，生态完善，便于快速构建 RESTful 服务 |
| AI 框架 | LangChain4j | 1.12.1 | Java 生态中最成熟的 LLM 应用框架，支持工具调用、记忆管理 |
| 数据库 | MySQL | 8.0+ | 关系型数据库，适合结构化销售数据存储 |
| 缓存 | Redis | 6.0+ | 高性能缓存，用于会话管理和数据缓存 |
| 认证 | Sa-Token | 1.39.0 | 轻量级权限框架，集成简单，功能完善 |
| ORM | Spring Data JPA | - | 简化数据库操作，提高开发效率 |

### 2.2 前端技术栈

| 分类 | 技术 | 版本 | 选型理由 |
|------|------|------|----------|
| 框架 | Vue.js | 3.5.13 | 响应式设计，组合式 API，性能优异 |
| 构建工具 | Vite | 6.1.0 | 快速构建，热更新，现代化工具链 |
| UI 组件 | Element Plus | 2.9.7 | 丰富的组件库，美观的设计 |
| 状态管理 | Pinia | 2.3.0 | Vue 3 官方推荐，简单直观 |
| 图表 | ECharts | 5.5.1 | 强大的数据可视化能力，支持多种图表类型 |
| HTTP 客户端 | Axios | 1.7.9 | 成熟稳定，支持拦截器和请求取消 |

### 2.3 AI 服务

| 服务 | 说明 |
|------|------|
| 模型 | 通义千问 (qwen-max) |
| 接入方式 | DashScope OpenAI 兼容接口 |
| 温度参数 | 0.1（数据分析场景，低随机性） |

---

## 3. 架构原则

### 3.1 架构风格

- **分层架构**：Controller → Service → Repository → Database
- **AI Agent 模式**：基于 LangChain4j 的工具调用框架
- **RESTful API**：标准化接口设计

### 3.2 设计原则

| 原则 | 说明 |
|------|------|
| SOLID | 单一职责、开闭原则、里氏替换、接口隔离、依赖倒置 |
| 依赖注入 | 使用 Spring DI 管理组件依赖 |
| 无状态设计 | REST API 设计遵循无状态原则 |
| 幂等性 | 确保重复请求不产生副作用 |

### 3.3 目录结构规范

```
backend/
├── src/main/java/com/mk/salesAgent/
│   ├── agent/          # AI Agent 接口定义
│   ├── config/         # 配置类
│   ├── controller/     # REST API 控制器
│   ├── dto/            # 数据传输对象
│   ├── entity/         # JPA 实体类
│   ├── memory/         # 对话记忆持久化
│   ├── repository/     # 数据访问层
│   ├── service/        # 业务逻辑层
│   └── tool/           # AI 工具类（@Tool 注解）
└── src/main/resources/
    ├── db/             # 数据库脚本
    └── application.yml # 应用配置
```

---

## 4. 命名规范

### 4.1 包命名

- 全部小写，用点分隔
- 反域名命名：`com.mk.salesAgent.module`

### 4.2 类命名

| 类型 | 命名规则 | 示例 |
|------|----------|------|
| 控制器 | 模块名 + Controller | SalesAgentController |
| 服务 | 模块名 + Service | SalesQueryService |
| 仓库 | 实体名 + Repository | SalesOrderRepository |
| 实体 | 业务名称（单数） | SalesOrder |
| DTO | 业务名称 + DTO | MonthlyTrendDTO |
| 工具 | 功能名 + Tool | SalesQueryTool |

### 4.3 方法命名

- 动词开头，采用驼峰命名
- 清晰表达业务意图

| 操作类型 | 前缀 | 示例 |
|----------|------|------|
| 查询 | find/get/query | findById, getTopReps |
| 创建 | create/add | createOrder |
| 更新 | update | updateOrderStatus |
| 删除 | delete/remove | deleteOrder |
| 保存 | save | saveOrder |

### 4.4 变量命名

- 采用驼峰命名
- 见名知意，避免缩写

### 4.5 数据库命名

| 对象 | 规则 | 示例 |
|------|------|------|
| 表名 | sa_ + 业务名（复数） | sa_sales_order |
| 字段名 | 小写下划线 | order_no, created_at |
| 主键 | id（BIGINT） | id |

---

## 5. API 设计规范

### 5.1 路径命名

- 全部小写，用连字符分隔
- 资源名词用复数

```
/auth/login           # 动词路径（特殊情况）
/agent/chat           # 动词路径（特殊情况）
/api/v1/orders        # 资源路径
/api/v1/orders/{id}   # 资源详情
```

### 5.2 HTTP 方法

| 方法 | 用途 |
|------|------|
| GET | 查询资源 |
| POST | 创建资源 |
| PUT | 完整更新资源 |
| PATCH | 部分更新资源 |
| DELETE | 删除资源 |

### 5.3 响应格式

```json
{
  "code": 200,
  "message": "success",
  "data": {},
  "timestamp": "2024-01-15T10:30:00Z"
}
```

### 5.4 错误处理

| 状态码 | 含义 |
|--------|------|
| 400 | 请求参数错误 |
| 401 | 未授权 |
| 403 | 拒绝访问 |
| 404 | 资源不存在 |
| 500 | 服务器内部错误 |

---

## 6. 代码风格规范

### 6.1 格式规范

- 使用 4 空格缩进
- 每行最多 120 字符
- 文件编码 UTF-8

### 6.2 注释规范

- 使用 Javadoc 格式
- 类和方法必须有注释
- 复杂逻辑需要注释说明

### 6.3 异常处理

- 使用全局异常处理器
- 不要捕获所有异常（catch Exception）
- 异常信息要清晰，便于排查

---

## 7. 安全规范

### 7.1 认证授权

- 使用 Sa-Token 进行认证
- 敏感操作需要权限校验
- Token 过期时间 24 小时

### 7.2 输入校验

- 使用 Jakarta Validation 进行参数校验
- 防止 SQL 注入（JPA 参数化查询）
- 防止 XSS 攻击（前端转义）

### 7.3 日志规范

- 日志级别：DEBUG < INFO < WARN < ERROR
- 生产环境关闭 DEBUG 日志
- 禁止记录敏感信息（密码、Token）

---

## 8. 变更记录

| 版本 | 日期 | 变更内容 | 作者 |
|------|------|----------|------|
| 1.0.0 | 2024-01-15 | 初始版本 | 架构师 |

import React, { useState } from 'react';
import {
  MenuFoldOutlined,
  BarChartOutlined,
  MenuUnfoldOutlined,
  UploadOutlined,
  UserOutlined,
  TableOutlined,
  VideoCameraOutlined,
} from '@ant-design/icons';
import { Button, Layout, Menu, theme } from 'antd';
import { ItemType, MenuItemType } from 'antd/es/menu/interface';
import { Link, Outlet } from 'react-router-dom';

const { Header, Sider, Content } = Layout;
const items: ItemType<MenuItemType>[] = [
  {
    key: "0",
    icon: <BarChartOutlined />,
    label: 'Dashboard',
  },
  {
    key: '1',
    icon: <TableOutlined />,
    label: 'Data',
    children: [{
      key: '1-1',
      icon: <UserOutlined />,
      label: <Link to={"/admin/account"}>Account</Link>,
    }, {
      key: "1-2",
      icon: <TableOutlined />,
      label: <Link to={"/admin/role"}>Role</Link>,
    }, {
      key: "1-3",
      icon: <TableOutlined />,
      label: <Link to={"/admin/permission"}>Permission</Link>,

    },{
      key: "1-4",
      icon: <TableOutlined />,
      label: <Link to={`/admin/exam`}>Exam</Link>
    },{
      key:"1-5",
      icon: <TableOutlined />,
      label: <Link to={`/admin/exam-category`}>Exam Category</Link>
    }]
  },
  {
    key: '2',
    icon: <VideoCameraOutlined />,
    label: 'nav 2',
  },
  {
    key: '3',
    icon: <UploadOutlined />,
    label: 'nav 3',
  },
]
const App: React.FC = () => {
  const [collapsed, setCollapsed] = useState(false);
  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();

  return (
    <Layout>
      <Sider trigger={null} style={{ height: "100vh" }} collapsible collapsed={collapsed}>
        <div className="demo-logo-vertical" />
        <Menu
          theme="dark"
          mode="inline"
          defaultSelectedKeys={['1']}
          items={items}
        />
      </Sider>
      <Layout>
        <Header style={{ padding: 0, background: colorBgContainer }}>
          <Button
            type="text"
            icon={collapsed ? <MenuUnfoldOutlined /> : <MenuFoldOutlined />}
            onClick={() => setCollapsed(!collapsed)}
            style={{
              fontSize: '16px',
              width: 64,
              height: 64,
            }}
          />
        </Header>
        <Content
          style={{
            margin: '24px 16px',
            padding: 24,
            minHeight: 280,
            background: colorBgContainer,
            borderRadius: borderRadiusLG,
          }}
        >
          <Outlet />
        </Content>
      </Layout>
    </Layout>
  );
};

export default App;
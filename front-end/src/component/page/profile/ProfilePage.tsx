import React from 'react';
import { AppstoreOutlined, UserOutlined } from '@ant-design/icons';
import type { MenuProps } from 'antd';
import { Layout, Menu, theme } from 'antd';
import { NavLink, Outlet } from 'react-router-dom';
const { Sider, Content } = Layout;
type MenuItem = Required<MenuProps>['items'][number];

const items: MenuItem[] = [
    {
        key: 'sub1',
        label: "Profile",
        icon: <UserOutlined />,
        children: [{
            key: 'g0',
            label: <NavLink to={"/profile/account"}>Account</NavLink>,
        },
        {
            key: 'g1',
            label: <NavLink to={"/profile/change-info"}>Change info</NavLink>,

        },
        {
            key: 'g2',
            label: <NavLink to={"/profile/change-password"}>Change Password</NavLink>,
        },
        ],
    },
    {
        key: 'sub2',
        label: 'Exam',
        icon: <AppstoreOutlined />,
        children: [
            {
                key: '5', label: <NavLink to={"/profile/manager-exam"}>
                    Manager
                </NavLink>
            },
            {
                key: '6', label: <NavLink to={"/profile/exam-result"}>
                    History
                </NavLink>
            },
        ],
    }
];

const ProfilePage = () => {
    const {
        token: { colorBgContainer, borderRadiusLG },
    } = theme.useToken();
    const onClick: MenuProps['onClick'] = (e) => {
        console.log('click ', e);
    };

    return (
        <Content className='container'>
            <Layout
                style={{ padding: '24px 0', background: colorBgContainer, borderRadius: borderRadiusLG }}
            >
                <Sider style={{ background: colorBgContainer }} width={200}>
                    <Menu
                        onClick={onClick}
                        defaultSelectedKeys={['1']}
                        defaultOpenKeys={['sub1']}
                        mode="inline"
                        items={items}
                    />
                </Sider>
                <Content>
                    <Outlet />
                </Content>
            </Layout>
        </Content>
    );
};

export default ProfilePage;
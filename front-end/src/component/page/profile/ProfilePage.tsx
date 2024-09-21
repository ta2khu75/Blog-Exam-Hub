import React from 'react';
import { AppstoreOutlined, UserOutlined } from '@ant-design/icons';
import type { MenuProps } from 'antd';
import { Layout, Menu, theme } from 'antd';
import { NavLink, Outlet } from 'react-router-dom';
const { Sider, Content } = Layout;
type MenuItem = Required<MenuProps>['items'][number];

const items: MenuItem[] = [
    {
        key: 'profile',
        label: "Profile",
        icon: <UserOutlined />,
        children: [{
            key: 'account',
            label: <NavLink to={"/profile/account"}>Account</NavLink>,
        },
        {
            key: 'change-info',
            label: <NavLink to={"/profile/change-info"}>Change info</NavLink>,

        },
        {
            key: 'change-password',
            label: <NavLink to={"/profile/change-password"}>Change Password</NavLink>,
        },
        ],
    },
    {
        key: 'exam',
        label: 'Exam',
        icon: <AppstoreOutlined />,
        children: [
            {
                key: 'manager-exam', label: <NavLink to={"/profile/manager-exam"}>
                    Manager
                </NavLink>
            },
            {
                key: 'exam-results', label: <NavLink to={"/profile/exam-result"}>
                    History
                </NavLink>
            },
        ],
    }, {
        key: "blog",
        label: "Blog",
        icon: <AppstoreOutlined />,
        children: [
            {
                key: "manager-blog",
                label: <NavLink to={"/profile/manager-blog"}>Manger</NavLink>
            }, {
                key: "blog-post",
                label: <NavLink to={"/profile/blog-post"}></NavLink>
            }
        ]
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
        <Content className='container vh-100'>
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
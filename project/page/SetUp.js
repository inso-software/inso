/**
 * Created by inshow on 2018/12/29.
 */

import React from 'react';
import {createStackNavigator, createAppContainer} from 'react-navigation';
import TitleBar from './views/TitleBar';
import HomePage from './HomePage';
import MyPage from './MyPage';

const RootStack = createStackNavigator(
    {
        HomePage: HomePage,
        MyPage: MyPage,
    },
    {
        initialRouteName: 'HomePage',
        defaultNavigationOptions: ({navigation}) => ({
            gesturesEnabled: true,
            header: <TitleBar title={navigation.state.params ? navigation.state.params.title : ''}
                              type={'dark'}
                              onPressLeft={() => {
                                  navigation.goBack();
                              }}/>,
        }),
    }
);
const AppContainer = createAppContainer(RootStack);

export default class SetUp extends React.Component {
    //在这里可以进行一些初始化配置
    render() {
        return <AppContainer />
    }
}
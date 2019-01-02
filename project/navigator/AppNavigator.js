/**
 * Created by inshow on 2019/1/2.
 */
import React from 'react';
import {createStackNavigator, createAppContainer} from 'react-navigation';

import WelcomePage from '../pages/WelcomePage';
import HomePage from '../pages/HomePage';
import ProductPage from '../pages/ProductPage';
import MyPage from '../pages/MyPage';

const RootStack = createStackNavigator(
    {
        WelcomePage: {
            screen: WelcomePage
        },
        HomePage: {
            screen: HomePage
        },
        ProductPage: {
            screen: ProductPage
        },
        MyPage: {
            screen: MyPage
        },
    },
    {
        defaultNavigationOptions: {
            header: null,
        },
    }
);
export default AppNavigator = createAppContainer(RootStack);

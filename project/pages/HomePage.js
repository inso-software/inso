/**
 * Created by inshow on 2018/12/29.
 */
import React from 'react';
import {
    StyleSheet,
    Text,
    View,
    Image,
    TouchableOpacity,
    Button,
    NativeModules,
    DeviceEventEmitter
} from 'react-native';

import TabNavigator from 'react-native-tab-navigator';
import Toast, {DURATION} from 'react-native-easy-toast'
import NavigatorUtil from '../utils/NavigatorUtil';
import BaseComponent from './BaseComponent';
import SafeAreaViewPlus from '../common/SafeAreaViewPlus';
import ThemeFactory, {ThemeFlags} from '../utils/ThemeFactory';
import ProductPage from './ProductPage';
import MyPage from './MyPage';

export const ACTION_HOME = {
    A_SHOW_TOAST: 'showToast',
    A_RESTART: 'restart',
    A_THEME: 'theme',
    A_HOME_TAB_SELECT: 'home_tab_select'
};
export const FLAG_TAB = {
    flag_product: 'tb_product',
    flag_my: 'tb_my'
};
export const EVENT_TYPE_HOME_TAB_SELECT = "home_tab_select";

export default class HomePage extends BaseComponent {
    static navigationOptions = () => {
        return {
            gesturesEnabled: false
        };
    };

    constructor(props) {
        super(props);
        this.params = this.props.navigation.state.params;
        let selectedTab = this.params.selectedTab ? this.params.selectedTab : 'tb_product';
        this.state = {
            selectedTab: selectedTab,
            theme: this.params.theme || ThemeFactory.createTheme(ThemeFlags.Default),
        }
    }

    componentDidMount() {
        super.componentDidMount();
        this.listener = DeviceEventEmitter.addListener('ACTION_HOME',
            (action, params) => this.onAction(action, params));
    }

    /**
     * 通知回调事件处理
     * @param action
     * @param params
     */
    onAction(action, params) {
        if (ACTION_HOME.A_RESTART === action) {
            this.onRestart(params)
        } else if (ACTION_HOME.A_SHOW_TOAST === action) {
            this.toast.show(params.text, DURATION.LENGTH_SHORT);
        }
    }

    componentWillUnmount() {
        super.componentWillUnmount();
        if (this.listener) {
            this.listener.remove();
        }
    }

    /**
     * 重启首页
     * @param jumpToTab 默认显示的页面
     */
    onRestart(jumpToTab) {
        NavigatorUtil.resetToHomePage({
            ...this.params,
            selectedTab: jumpToTab,
            navigation: this.props.navigation
        })
    }

    _renderTab(Component, selectedTab, title, renderIcon, renderSelectedIcon) {
        return (
            <TabNavigator.Item
                selected={this.state.selectedTab === selectedTab}
                selectedTitleStyle={this.state.theme.styles.selectedTitleStyle}
                title={title}
                renderIcon={() => <Image style={styles.image}
                                         source={renderIcon}/>}
                renderSelectedIcon={() => <Image style={[styles.image]}
                                                 source={renderSelectedIcon}/>}
                onPress={() => this.onTabClick(this.state.selectedTab, selectedTab)}
            >
                <Component {...this.props} theme={this.state.theme}/>
            </TabNavigator.Item>
        )
    }

    render() {
        const Root = <SafeAreaViewPlus
            topColor={this.state.theme.themeColor}
            bottomInset={false}
        >
            <TabNavigator>
                {this._renderTab(ProductPage, FLAG_TAB.flag_product, '产品', require('../resource/tabbar/ic_tabbar_home.png'), require('../resource/tabbar/ic_tabbar_home_selected.png'))}
                {this._renderTab(MyPage, FLAG_TAB.flag_my, '我的', require('../resource/tabbar/ic_tabbar_mine.png'), require('../resource/tabbar/ic_tabbar_mine_selected.png'))}
            </TabNavigator>
            <Toast ref={(toast) => this.toast = toast}/>
        </SafeAreaViewPlus>
        return Root;
    }

    onTabClick(from, to) {
        this.setState({selectedTab: to});
        DeviceEventEmitter.emit(EVENT_TYPE_HOME_TAB_SELECT, from, to)
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
    image: {
        height: 26,
        width: 26,
    }
});
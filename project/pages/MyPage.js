/**
 * Created by inshow on 2018/12/29.
 */
import React from 'react';
import {StyleSheet, Text, View, TouchableOpacity, Button, NativeModules} from 'react-native';

import NavigationBar from "../common/NavigationBar";
import BaseComponent from './BaseComponent';
import NavigatorUtil from '../utils/NavigatorUtil';
import ViewUtils from '../utils/ViewUtils';

export default class MyPage extends BaseComponent {
    constructor(props) {
        super(props);
        this.state = {
            customThemeViewVisible: false,
            theme: this.props.theme
        }
    }

    render() {
        let navigationBar =
            <NavigationBar
                title='我的'
                style={this.state.theme.styles.navBar}
                rightButton={ViewUtils.getMoreButton()}
            />;
        return (
            <View style={{flex: 1}}>
                {navigationBar}
            </View>
        );
    }
}
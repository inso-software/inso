/**
 * Created by inshow on 2019/1/2.
 */
import React from 'react';
import {StyleSheet, Text, View, TouchableOpacity, Button, NativeModules} from 'react-native';

import NavigationBar from "../common/NavigationBar";
import BaseComponent from './BaseComponent';

export default class ProductPage extends BaseComponent {
    constructor(props) {
        super(props);
        this.state = {
            customThemeViewVisible: false,
            theme: this.props.theme
        }
    }

    render() {
        let statusBar = {
            backgroundColor: this.state.theme.themeColor,
            barStyle: 'light-content',
        };
        let navigationBar =
            <NavigationBar
                title={'产品'}
                statusBar={statusBar}
                style={this.state.theme.styles.navBar}
            />;
        return (
            <View style={{flex: 1}}>
                {navigationBar}
            </View>
        );
    }
}
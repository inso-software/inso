/**
 * Created by inshow on 2019/1/2.
 */
import React, {Component} from 'react';
import {
    View,
    StyleSheet,
    Text,
} from 'react-native'
import NavigatorUtil from '../utils/NavigatorUtil';
import ThemeDao from '../utils/ThemeDao';

export default class WelcomePage extends Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        new ThemeDao().getTheme().then((data) => {
            this.theme = data;
        });
        this.timer = setTimeout(() => {
            NavigatorUtil.resetToHomePage({
                theme: this.theme,
                navigation: this.props.navigation
            })
        }, 500);
    }

    componentWillUnmount() {
        this.timer && clearTimeout(this.timer);
    }

    render() {
        return null;
    }
}
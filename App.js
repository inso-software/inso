'use strict';
import React from 'react';
import {
    AppRegistry,
    StyleSheet,
    Text,
    View,
    TouchableOpacity,
    ToastAndroid,
    NativeModules,
    DeviceEventEmitter
} from 'react-native';

const hybridModule = NativeModules.HybridModule;
export default class ReactNativePage extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            data:"No data",
            text1: 'callNative',
            text2: 'callNativeInvokeFuntion',
            text3: 'callNativeByPromise',
            text4: 'callNativeByEmitter',
        }
    }

    componentWillMount() {
        console.log("componentWillMount");
        let msg = hybridModule.HybridEvent;
        ToastAndroid.show("Get Constants:" + msg);
        DeviceEventEmitter.addListener('HybridEvent', (result)=> {
            this.setState({data:result});
        });

        hybridModule.getIntentData((result) =>{
            this.setState({data:result});
        })
    }

    render() {
        return (
            <View style={styles.container}>
                <Text style={styles.textStyle}>{this.state.data}</Text>
                <TouchableOpacity onPress={this._onPressButton.bind(this)}>
                    <Text style={styles.textStyle}>{this.state.text1}</Text>
                </TouchableOpacity>
                <TouchableOpacity onPress={this._onPressButton2.bind(this)}>
                    <Text style={styles.textStyle}>{this.state.text2}</Text>
                </TouchableOpacity>
                <TouchableOpacity onPress={this._onPressButton3.bind(this)}>
                    <Text style={styles.textStyle}>{this.state.text3}</Text>
                </TouchableOpacity>
                <TouchableOpacity onPress={this._onPressButton4.bind(this)}>
                    <Text style={styles.textStyle}>{this.state.text4}</Text>
                </TouchableOpacity>
            </View>
        )
    }

    _onPressButton() {
        hybridModule.callNative("Happy new year！");
    }

    _onPressButton2() {
        hybridModule.callNativeInvokeFuntion((result) => {
            this.setState({data: result});
        });
    }

    _onPressButton3() {
        hybridModule.callNativeByPromise().then((result) => {
            this.setState({data: result});
        }).catch((error) => {
            this.setState({data: 'error'});
        })
    }

    1

    _onPressButton4() {
        hybridModule.callNativeByEmitter();
    }
}

var styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        flexDirection: 'column',
    },
    textStyle: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    },
});

AppRegistry.registerComponent('ReactNativeAct', () => ReactNativePage);
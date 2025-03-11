import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  View,
} from 'react-native';
import React from 'react';
import Login from './src/components/Login';
import Register from './src/components/Register';

const App = () => {
  return (
    <View style={styles.mainFrame}>
      <StatusBar barStyle={'dark-content'} />
      <ScrollView>
        <Login></Login>
      </ScrollView>
    </View>
  );
};

export default App;

const styles = StyleSheet.create({
  mainFrame: {
    paddingTop: 15,
    paddingLeft: 5,
    paddingRight: 5,
  },
});

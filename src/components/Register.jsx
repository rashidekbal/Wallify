import {
  Image,
  SafeAreaView,
  ScrollView,
  Text,
  TextInput,
  TouchableOpacity,
  View,
} from 'react-native';
import React from 'react';
import style from '../styles/Register.style';

const Register = () => {
  return (
    <SafeAreaView>
      <View style={style.heroHolder}>
        <Text style={style.hero}>Welcome to wallify </Text>
        <Text style={style.heading}>Login or Sign Up to access your</Text>
        <Text style={style.heading}> account</Text>
      </View>
      <View style={style.formView}>
        <View style={style.pageType}>
          <TouchableOpacity>
            <View style={[style.Login_register, {backgroundColor: '#FFFFFF'}]}>
              <Text style={style.Login_register_text}>Login</Text>
            </View>
          </TouchableOpacity>
          <TouchableOpacity>
            <View style={[style.Login_register, {backgroundColor: '#E6F5FF'}]}>
              <Text style={style.Login_register_text}>Sign Up</Text>
            </View>
          </TouchableOpacity>
        </View>
        <View>
          <View
            style={{
              height: 57,
              width: 340,
              margin: 'auto',
              marginBottom: 13,
            }}>
            <TouchableOpacity>
              <View style={style.OtherLoginButton}>
                <Image
                  source={require('../icons/google.png')}
                  style={style.logo}></Image>
                <Text style={style.OtherLoginButton_text}>
                  Sign Up with Google
                </Text>
              </View>
            </TouchableOpacity>
          </View>
          <View
            style={{
              height: 57,
              width: 340,
              margin: 'auto',
              marginBottom: 13,
            }}>
            <TouchableOpacity>
              <View style={[style.OtherLoginButton]}>
                <Image
                  source={require('../icons/fb-icon.png')}
                  style={[style.logo, {height: 37, width: 37}]}></Image>
                <Text style={style.OtherLoginButton_text}>
                  Sign Up with FaceBook
                </Text>
              </View>
            </TouchableOpacity>
          </View>
          <View style={style.stroke}>
            <Text style={style.alt}>or continue with email</Text>
          </View>
        </View>
        <View style={style.EmailLogin}>
          <View style={style.inputSection}>
            <Image
              source={require('../icons/email-icon.png')}
              alt="logo"
              style={{height: 24, width: 24}}></Image>
            <TextInput
              placeholder="Email Address"
              style={style.input}></TextInput>
          </View>
          <View style={[style.inputSection, {marginTop: 18}]}>
            <Image
              source={require('../icons/lock-icon.png')}
              alt="logo"
              style={{height: 24, width: 24}}></Image>
            <TextInput placeholder="Password" style={style.input}></TextInput>
          </View>
          <View style={[style.inputSection, {marginTop: 18}]}>
            <Image
              source={require('../icons/lock-icon.png')}
              alt="logo"
              style={{height: 24, width: 24}}></Image>
            <TextInput
              placeholder="Re-type Password"
              style={style.input}></TextInput>
          </View>

          <View style={style.ActionButtonHolder}>
            <TouchableOpacity>
              <Text style={style.login_signup_btn}>Sign Up</Text>
            </TouchableOpacity>
          </View>
          <Text style={{fontSize: 13, textAlign: 'center'}}>
            By signing Up with an account, you agree to Wallify's
          </Text>
          <Text
            style={{textAlign: 'center', fontWeight: 'bold', marginBottom: 15}}>
            <TouchableOpacity>
              <Text> Terms of Services </Text>
            </TouchableOpacity>
            <TouchableOpacity disabled={true}>
              <Text
                style={{
                  color: '#A1A6AA',
                  fontSize: 13,
                  fontWeight: 'medium',
                }}>
                and{' '}
              </Text>
            </TouchableOpacity>

            <TouchableOpacity>
              <Text> Privacy Policy</Text>
            </TouchableOpacity>
          </Text>
        </View>
      </View>
    </SafeAreaView>
  );
};

export default Register;

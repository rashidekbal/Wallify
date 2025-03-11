import react from 'react';
import style from '../styles/Login.style.js';
import {
  Image,
  SafeAreaView,
  Text,
  TextInput,
  TouchableOpacity,
  View,
} from 'react-native';

export default function Login() {
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
            <View style={[style.Login_register, {backgroundColor: '#E6F5FF'}]}>
              <Text style={style.Login_register_text}>Login</Text>
            </View>
          </TouchableOpacity>
          <TouchableOpacity>
            <View style={[style.Login_register, {backgroundColor: '#FFFFFF'}]}>
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
                  Login with Google
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
                  Login with FaceBook
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
          <View style={{width: '37%'}}>
            <TouchableOpacity>
              <Text
                style={{
                  fontSize: 15,
                  fontWeight: 'regular',
                  marginTop: 9,
                  color: '#000000',
                }}>
                Forget passwrod?
              </Text>
            </TouchableOpacity>
          </View>

          <View style={style.ActionButtonHolder}>
            <TouchableOpacity>
              <Text style={style.login_signup_btn}>Login</Text>
            </TouchableOpacity>
          </View>
          <Text style={{fontSize: 13, textAlign: 'center'}}>
            By signing in with an account, you agree to Wallify's
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
}

/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import GameID from './GameID';
import ScoreTable from './ScoreTable';
import SettingsScreen from './SettingsScreen';
import SettingsButton from './SettingsButton';
import {SettingsContextProvider} from './SettingsContext';

import React, {useState} from 'react';
import {
  Dimensions,
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  View,
  useColorScheme,
} from 'react-native';

import {Colors} from 'react-native/Libraries/NewAppScreen';

function App(): JSX.Element {
  const isDarkMode = useColorScheme() === 'dark';

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  const [inSettings, setInSettings] = useState(false);

  const [gottem, setGottem] = useState(false);
  const [response, setResponse] = useState('');

  return (
    <SettingsContextProvider>
      <SafeAreaView style={backgroundStyle}>
        <StatusBar
          barStyle={isDarkMode ? 'light-content' : 'dark-content'}
          backgroundColor={backgroundStyle.backgroundColor}
        />
        <ScrollView
          contentInsetAdjustmentBehavior="automatic"
          style={backgroundStyle}>
          <View style={styles.settingsButtonView}>
            {!inSettings && <SettingsButton setInSettings={setInSettings} />}
          </View>
          <View
            style={{
              backgroundColor: isDarkMode ? Colors.black : Colors.white,
              height: Dimensions.get('window').height,
            }}>
            {inSettings && <SettingsScreen setInSettings={setInSettings} />}
            {!inSettings && !gottem && (
              <GameID setGottem={setGottem} setResponse={setResponse} />
            )}
            {!inSettings && gottem && <ScoreTable response={response} />}
          </View>
        </ScrollView>
      </SafeAreaView>
    </SettingsContextProvider>
  );
}

const styles = StyleSheet.create({
  settingsButtonView: {
    position: 'absolute',
    top: 20,
    right: 20,
    zIndex: 5,
  },
});

export default App;

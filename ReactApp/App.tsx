/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import GameID from './GameID';
import ScoreTable from './ScoreTable';

import React, { useState } from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  View,
  useColorScheme
} from 'react-native';

import {
  Colors
} from 'react-native/Libraries/NewAppScreen';

function App(): JSX.Element {
  const isDarkMode = useColorScheme() === 'dark';

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  const [gottem, setGottem] = useState(false)
  const [response, setResponse] = useState('')

  return (
    <SafeAreaView style={backgroundStyle}>
      <StatusBar
        barStyle={isDarkMode ? 'light-content' : 'dark-content'}
        backgroundColor={backgroundStyle.backgroundColor}
      />
      <ScrollView
        contentInsetAdjustmentBehavior="automatic"
        style={backgroundStyle}>
        <View
          style={{
            backgroundColor: isDarkMode ? Colors.black : Colors.white,
          }}>
          {
            !gottem && <GameID setGottem={setGottem} setResponse={setResponse} />
          }
          {
            gottem && <ScoreTable response={response} />
          }
        </View>
      </ScrollView>
    </SafeAreaView>
  );
}

export default App;

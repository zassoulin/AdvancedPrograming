import React, {useContext, useState} from 'react';

// @ts-ignore
const SettingsContext = React.createContext();

export function useSettingsContext() {
  return useContext(SettingsContext);
}

export function SettingsContextProvider({
  children,
}: {
  children: React.ReactNode;
}) {
  const [hostnameState, setHostnameState] = useState('localhost:8080');

  return (
    <SettingsContext.Provider value={{hostnameState, setHostnameState}}>
      {children}
    </SettingsContext.Provider>
  );
}

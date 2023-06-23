import React, {useContext, useState} from 'react';

export interface hostnameType {
  hostnameState: string;
  setHostnameState: React.Dispatch<React.SetStateAction<string>>;
}

// @ts-ignore
const SettingsContext = React.createContext<hostnameType>();

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

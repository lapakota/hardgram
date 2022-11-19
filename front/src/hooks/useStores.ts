import { useContext } from 'react';
import { RootStoreContext } from '../stores';

export const useStores = () => useContext(RootStoreContext);

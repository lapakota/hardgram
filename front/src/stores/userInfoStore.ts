import { makeAutoObservable } from 'mobx';
import { UserInfo } from '../typescript/models/User/UserInfo';
import {
  loadFromLocalStorage,
  LocalStorageKeys,
  saveToLocalStorage
} from '../utils/localStorageHelper';

export class UserInfoStore {
  public userInfo?: UserInfo;
  public token?: string;

  constructor() {
    makeAutoObservable(this);
    this.userInfo = loadFromLocalStorage(LocalStorageKeys.UserInfo);
    this.token = loadFromLocalStorage(LocalStorageKeys.Token);
  }

  initStore(userInfo: UserInfo, token: string) {
    this.userInfo = userInfo;
    this.token = token;
    saveToLocalStorage(LocalStorageKeys.UserInfo, userInfo);
    saveToLocalStorage(LocalStorageKeys.Token, token);
  }

  logOut() {
    this.userInfo = undefined;
    this.token = undefined;
    saveToLocalStorage(LocalStorageKeys.UserInfo, '');
    saveToLocalStorage(LocalStorageKeys.Token, '');
  }
}

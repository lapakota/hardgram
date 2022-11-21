import { makeAutoObservable } from 'mobx';
import { UserInfoModel } from '../typescript/models/User/UserInfoModel';
import {
  loadFromLocalStorage,
  LocalStorageKeys,
  saveToLocalStorage
} from '../utils/localStorageHelper';

export class UserInfoStore {
  public userInfo?: UserInfoModel;
  public token?: string;

  constructor() {
    makeAutoObservable(this);
    this.userInfo = loadFromLocalStorage(LocalStorageKeys.UserInfo);
    this.token = loadFromLocalStorage(LocalStorageKeys.Token);
  }

  initStore(userInfo: UserInfoModel, token: string) {
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

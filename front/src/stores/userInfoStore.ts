import { makeAutoObservable } from 'mobx';
import { UserModel } from '../typescript/models/User/UserModel';
import {
  loadFromLocalStorage,
  LocalStorageKeys,
  saveToLocalStorage
} from '../utils/localStorageHelper';

export class UserInfoStore {
  public userInfo?: UserModel;
  public token?: string;

  constructor() {
    makeAutoObservable(this);
    this.userInfo = loadFromLocalStorage(LocalStorageKeys.UserInfo);
    this.token = loadFromLocalStorage(LocalStorageKeys.Token);
  }

  initStore(userInfo: UserModel, token: string) {
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

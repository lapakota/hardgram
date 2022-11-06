import { makeAutoObservable } from 'mobx';

// TODO Написать мобыксовый стор для хранения данных о пользователе
class UserStore {
  constructor() {
    makeAutoObservable(this);
  }
}

export const userStore = new UserStore();

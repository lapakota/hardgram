import { makeAutoObservable } from 'mobx';
import { PostModel as Post } from '../typescript/models/Post/PostModel';

export class PostsStore {
  public posts: Array<Post> = [];

  constructor() {
    makeAutoObservable(this);
  }
}

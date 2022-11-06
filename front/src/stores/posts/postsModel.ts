import { makeAutoObservable } from 'mobx';
import { PostModel as Post } from '../../api/models/Post/PostModel';

class PostsModel {
  public posts: Array<Post> = [];
  constructor() {
    makeAutoObservable(this);
  }
}

const postsStore = new PostsModel();
export default postsStore;

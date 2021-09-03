import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

export class Blog{
  constructor(public id: number,public title: string,public categories: string,public content: string){}
}

@Injectable({
  providedIn: 'root'
})
export class BlogService {

  constructor(private httpClient : HttpClient) { }

  getBlogs(){
    return this.httpClient.get<Blog[]>('http://localhost:4545/api/blogs');                   
  }

  getBlog(id: any){
    return this.httpClient.get<Blog>(`http://localhost:4545/api/blog/${id}`);                 
  }

  addBlog(blog:Blog){
    return this.httpClient.post('http://localhost:4545/api/blog/add',blog);             
  }

  editBlog(id: number,blog:Blog){
    return this.httpClient.post(`http://localhost:4545/api/blog/edit/${id}`,blog)            
  }

  delete(id:number){
    return this.httpClient.get(`http://localhost:4545/api/blog/delete/${id}`);
  }

}


import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddBlogComponent } from './add-blog/add-blog.component';
import { EditBlogComponent } from './edit-blog/edit-blog.component';
import { HomeComponent } from './home/home.component';
import { ShowBlogComponent } from './show-blog/show-blog.component';

const routes: Routes = [
  {path: 'blog/add',component: AddBlogComponent},
  {path: 'blog/:id',component:ShowBlogComponent},
  {path: 'blog/edit/:id',component:EditBlogComponent},
  {path: 'home',component:HomeComponent},
  {path: '**',component:HomeComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

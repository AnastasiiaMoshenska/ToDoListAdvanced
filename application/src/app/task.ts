import {Category} from "./category";

export interface Task{
  id: number;
  name: string;
  description: string;
  deadline: Date;
  category: Category
}

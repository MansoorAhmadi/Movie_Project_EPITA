import { Moment } from 'moment';
import { IMovieUser } from 'app/shared/model/movie-user.model';
import { IAddress } from 'app/shared/model/address.model';

export interface IContact {
  id?: number;
  name?: string;
  birthDate?: Moment;
  gender?: string;
  email?: string;
  movieUser?: IMovieUser;
  addresses?: IAddress[];
}

export const defaultValue: Readonly<IContact> = {};

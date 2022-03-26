import { ISeenMovie } from 'app/shared/model/seen-movie.model';
import { IRole } from 'app/shared/model/role.model';
import { IContact } from 'app/shared/model/contact.model';

export interface IMovieUser {
  id?: number;
  username?: string;
  seenMovies?: ISeenMovie[];
  roles?: IRole[];
  contact?: IContact;
}

export const defaultValue: Readonly<IMovieUser> = {};

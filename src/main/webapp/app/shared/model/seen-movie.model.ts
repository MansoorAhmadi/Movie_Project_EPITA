import { Moment } from 'moment';
import { IMovie } from 'app/shared/model/movie.model';
import { IMovieUser } from 'app/shared/model/movie-user.model';

export interface ISeenMovie {
  id?: number;
  date?: Moment;
  movie?: IMovie;
  movieUser?: IMovieUser;
}

export const defaultValue: Readonly<ISeenMovie> = {};

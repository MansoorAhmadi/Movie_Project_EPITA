import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './movie.reducer';
import { IMovie } from 'app/shared/model/movie.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import VideoPlayer from 'react-video-js-player';

export interface IMovieProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Movie = (props: IMovieProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { movieList, match, loading } = props;
  return (
    <div>

      <h2 id="movie-heading">
        Movies
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Movie
        </Link>
      </h2>
      <div className="table-responsive">
        {movieList && movieList.length > 0 ? (
          <Table responsive>
            <thead>
            <tr>
              <th>  </th>
              <th>Title</th>
              <th>Date</th>
            </tr>
            </thead>
            <tbody>
            {movieList.map((movie, i) => (
              <tr key={`entity-${i}`}>
                <td>
                  <Button color="info" size="sm">
                    <div>
                      <VideoPlayer
                        controls={true}
                        src={movie.externalId}
                        poster='content/images/Poster.png'
                        width="720"
                        height="420"
                      />
                    </div>
                  </Button>
                </td>
                <td>{movie.title}</td>
                <td>
                  <TextFormat type="date" value={movie.date} format={APP_DATE_FORMAT} />
                </td>
              </tr>
            ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Movies found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ movie }: IRootState) => ({
  movieList: movie.entities,
  loading: movie.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Movie);

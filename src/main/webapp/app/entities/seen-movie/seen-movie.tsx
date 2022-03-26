import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './seen-movie.reducer';
import { ISeenMovie } from 'app/shared/model/seen-movie.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISeenMovieProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const SeenMovie = (props: ISeenMovieProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { seenMovieList, match, loading } = props;
  return (
    <div>
      <h2 id="seen-movie-heading">
        Seen Movies
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Seen Movie
        </Link>
      </h2>
      <div className="table-responsive">
        {seenMovieList && seenMovieList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Date</th>
                <th>Movie</th>
                <th>Movie User</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {seenMovieList.map((seenMovie, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${seenMovie.id}`} color="link" size="sm">
                      {seenMovie.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={seenMovie.date} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{seenMovie.movie ? <Link to={`movie/${seenMovie.movie.id}`}>{seenMovie.movie.id}</Link> : ''}</td>
                  <td>{seenMovie.movieUser ? <Link to={`movie-user/${seenMovie.movieUser.id}`}>{seenMovie.movieUser.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${seenMovie.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${seenMovie.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${seenMovie.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Seen Movies found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ seenMovie }: IRootState) => ({
  seenMovieList: seenMovie.entities,
  loading: seenMovie.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SeenMovie);

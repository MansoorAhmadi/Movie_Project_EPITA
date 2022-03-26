import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './movie-user.reducer';
import { IMovieUser } from 'app/shared/model/movie-user.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMovieUserProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const MovieUser = (props: IMovieUserProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { movieUserList, match, loading } = props;
  return (
    <div>
      <h2 id="movie-user-heading">
        Movie Users
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Movie User
        </Link>
      </h2>
      <div className="table-responsive">
        {movieUserList && movieUserList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Role</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {movieUserList.map((movieUser, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${movieUser.id}`} color="link" size="sm">
                      {movieUser.id}
                    </Button>
                  </td>
                  <td>{movieUser.username}</td>
                  <td>
                    {movieUser.roles
                      ? movieUser.roles.map((val, j) => (
                          <span key={j}>
                            <Link to={`role/${val.id}`}>{val.id}</Link>
                            {j === movieUser.roles.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${movieUser.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${movieUser.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${movieUser.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Movie Users found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ movieUser }: IRootState) => ({
  movieUserList: movieUser.entities,
  loading: movieUser.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MovieUser);

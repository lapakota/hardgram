import React from 'react';
import { Stack } from '@mui/material';

export function Photo({ photo }: { photo: string }): React.ReactElement {
  return (
    <Stack justifyContent={'center'} alignItems={'center'} sx={{ height: 550 }}>
      <img
        src={photo}
        alt={'post photo'}
        style={{ objectFit: 'contain', maxWidth: 500, maxHeight: 550 }}
      />
    </Stack>
  );
}
